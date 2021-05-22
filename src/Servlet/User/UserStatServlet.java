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
import DatabaseManager.JSONBuilder;
import Servlet.Servlet_Base;



/**
 * Servlet that check if the user il logged
 * @author  Alessio Tranzocchi & Matteo Catalano
 *
 */
@WebServlet(name="UserStatServlet", urlPatterns="/getusrstat.jsp")
public class UserStatServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Open the session
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("logged");
		
		if(email != null) {
			String usernameQ = "SELECT username FROM utente u WHERE u.email = \""+email+"\"";
			String datas = "SELECT * FROM balance b WHERE b.user_id = ("+usernameQ+")";

			try {
				
				//Execute the query in order to find if there is any user that met the criteria
				ResultSet rs = DBManager.exDQLQuery(datas);
				
				//Check if there is a result
				if(rs != null) {
					if(!rs.isClosed()) {
						JSONBuilder builder = new JSONBuilder();
						builder.append("username", rs.getString("user_id"));
						builder.append("balance", rs.getString("user_balance"));
						response.getWriter().write(builder.build());
						rs.close();
					}
				}
				else {
					//If not the data are incorrect , set the error and redirect
					response.getWriter().write("false");
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else {
			response.getWriter().write("notLogged");
		}
	}
}