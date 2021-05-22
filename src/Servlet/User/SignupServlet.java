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
@WebServlet(name="SignupServlet", urlPatterns="/signup.jsp")
public class SignupServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Open the session
		HttpSession session = request.getSession();
		
		//Get the data
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String indirizzo = request.getParameter("indirizzo");
		String nazione = request.getParameter("nazione");
		
		
		// Building Query's
		String newBalance = "INSERT INTO balance (user_id) VALUES(\""+username+"\")";
		String signup_query = "INSERT INTO Utente (username,email,password,nazione,indirizzo) VALUES(\""+username+"\",\""+email+"\",\""+password+"\",\""+nazione+"\",\""+indirizzo+"\")";
		
		try {
			
			//CHECK IF EMAIL ALREADY EXISTS
			String emailTaken = "SELECT * FROM Utente u WHERE u.email = \""+email+"\"";
			String usernameTaken = "SELECT * FROM Utente u WHERE u.username = \""+username+"\"";
			
			ResultSet eT = DBManager.exDQLQuery(emailTaken);
			ResultSet uT = DBManager.exDQLQuery(usernameTaken);
			
			if(eT.next()) {
				response.getWriter().write("1");
				eT.close();
			}
			else if(uT.next()) {
				response.getWriter().write("2");
				uT.close();
			}
			else {
				//If email is available, signup
				boolean rs1 = DBManager.exDMLQuery(signup_query);
				boolean rs2 = DBManager.exDMLQuery(newBalance);
				
				//Check if there was an error
				if(rs1 == true && rs2 == true) {
					session.setAttribute("logged", null);
					response.getWriter().write("home.html");
				}
				else {
					response.getWriter().write("3");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}