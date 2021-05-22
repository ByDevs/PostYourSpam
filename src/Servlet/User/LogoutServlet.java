package Servlet.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Servlet.Servlet_Base;



/**
 * Servlet that handle the logout of an user
 * @author Alessio Tranzocchi & Matteo Catalano
 *
 */
@WebServlet(name="LogoutServlet", urlPatterns="/logout.jsp")
public class LogoutServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Open the session
		HttpSession session = request.getSession();
		
		//If is logged, perform a logout
		if(session.getAttribute("logged") != null) {
			
			session.setAttribute("logged",null);
			response.sendRedirect("home.html");
		
		}
	}
}
