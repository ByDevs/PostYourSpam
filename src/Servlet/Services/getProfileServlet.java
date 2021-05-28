package Servlet.Services;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DatabaseManager.DBManager;
import DatabaseManager.JSONBuilder;
import Servlet.Servlet_Base;

/**
 * Servlet that handle the request of a post by a user
 * @author Alessio Tranzocchi & Matteo Catalano
 *
 */

@WebServlet(name="getProfileServlet", urlPatterns="/getprofile.jsp")
public class getProfileServlet extends Servlet_Base{
	private static final long serialVersionUID = 8484501789787L;
	
	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String email;
		email = (String) session.getAttribute("logged");
		if(email != null) {
			try {
				
				String usernameQuery = "SELECT username FROM utente where email= \"" + email + "\"";
				String dataQuery = "SELECT * FROM balance b, utente u WHERE b.user_id = (" + usernameQuery + ") and u.email = \"" + email + "\"";
				
				ResultSet userData = DBManager.exDQLQuery(dataQuery);
			
				if(userData != null) {
					String username = userData.getString("username");
					String nazione = userData.getString("nazione");
					String indirizzo = userData.getString("indirizzo");
					String balance = userData.getString("user_balance");
					String profile = email + "|" + username + "|" + nazione + "|" + indirizzo + "|" + balance;
					response.getWriter().write(profile);
					userData.close();
				}
			
			}catch(SQLException e) {
				response.getWriter().write("2");
				e.printStackTrace();
			}
		}
		
	}
	
}
