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
 * Servlet that handle the login of an user
 * @author Alessio Tranzocchi & Matteo Catalano
 *
 */

@WebServlet(name="editProfileServlet", urlPatterns="/editProfile.jsp")
public class editProfileServlet extends Servlet_Base {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String address;
		String country;
		String oldPassword;
		String newPassword;
		
		
		String email = (String) session.getAttribute("logged");
		if(email != null) {
			//get request's data
			address = request.getParameter("address");
			country = request.getParameter("country");
			oldPassword = request.getParameter("oldPassword");
			newPassword = request.getParameter("newPassword");
			
			//check if user want to edit only address or address
			if(!oldPassword.isEmpty() && !newPassword.isEmpty()) {
				//check if newPassword is different from the oldPassword
				if(!oldPassword.equals(newPassword)) {
					String updateQuery = "UPDATE Utente SET nazione = '" + country + "', indirizzo = '" + address + "', password = '"+ newPassword + "' WHERE email = '"+ email + "'AND password = '" + oldPassword + "'";
					boolean querySuccess = DBManager.exDMLQuery(updateQuery);
					if(!querySuccess) {
						//query problem
						response.getWriter().write("1");
					}
				}
				else {
					response.getWriter().write("2");
					return;
				}
			}
			//edit only address and country
			else if(!address.isEmpty()) {
				String updateQueryNoPass = "UPDATE Utente SET nazione = '" + country + "', indirizzo = '" + address + "' WHERE email = '"+ email + "'";
				boolean success = DBManager.exDMLQuery(updateQueryNoPass);
				if(!success) {
					//query problem
					response.getWriter().write("1");
				}
			}
		}
	}
	
	
}
