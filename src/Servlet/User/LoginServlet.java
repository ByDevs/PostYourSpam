package Servlet.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseManager.DBManager;
import Servlet.Servlet_Base;

/**
 * Servlet that handle the login of an user
 * @author AlessioTranzocchi
 *
 */
@WebServlet(name="LoginServlet", urlPatterns="/login.jsp")
public class LoginServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;
	

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Open the session
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String query = "SELECT * FROM Utente u WHERE u.email = \""+email+"\" AND u.password = \""+password+"\"";
		
		System.out.println(query);
		
		try {
			//Execute the query in order to find if there is any user that met the criteria
			ResultSet rs = DBManager.exDQLQuery(query);
			//Check if there is a result
			if(rs != null && rs.next()) {
				session.setAttribute("logged", email);
				response.getWriter().write("true");
				rs.close();
			}
			else {
				//If not the data are incorrect , set the error and redirect
				response.getWriter().write("false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}