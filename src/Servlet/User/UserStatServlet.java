package Servlet.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseManager.JSONBuilder;
import Servlet.Servlet_Base;



/**
 * Servlet that handle the login of an user
 * @author AlessioTranzocchi
 *
 */
@WebServlet(name="UserStatServlet", urlPatterns="/getusrstat.jsp")
public class UserStatServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Open the session
		HttpSession session = request.getSession();
		
		if(session.getAttribute("logged") != null) {
			
			String username = "prova1";
			String balance = "prova2";
			
			
			JSONBuilder builder = new JSONBuilder();
			builder.append("username", username);
			builder.append("balance", balance);
			
			response.getWriter().write(builder.build());
		}
		else {
			response.getWriter().write("notLogged");
		}
		
	}
}
