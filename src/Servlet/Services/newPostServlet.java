package Servlet.Services;

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
@WebServlet(name="newPostServlet", urlPatterns="/newpost.jsp")
public class newPostServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;
	

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Open the session
		HttpSession session = request.getSession();
		
		String email = (String) session.getAttribute("logged");
		
		if(email != null) {
			
			String usernameQ = "SELECT username FROM utente u WHERE u.email = \""+email+"\"";

			String username = "";
			
			String titolo = request.getParameter("titolo");
			String sottotitolo = request.getParameter("sottotitolo");
			String body = request.getParameter("bodyPost");
			String link = request.getParameter("link");
			String reward = request.getParameter("reward");

			try {
				
				ResultSet rs1 = DBManager.exDQLQuery(usernameQ);
				if(rs1 != null && rs1.next()) {
					username = rs1.getString("username");
					rs1.close();
					
					//Assert that the user have the required 
					String assureBalance = "SELECT * FROM balance b WHERE b.user_id = \""+username+"\" and b.user_balance > "+reward;
					
					System.out.println(assureBalance);
					
					ResultSet rs2 = DBManager.exDQLQuery(assureBalance);
					
					if(rs2 != null && rs2.next()) {
						rs2.close();
						
						String newPost_query = "INSERT INTO post (username,titolo,sintesi,body,link,reward) VALUES(\""+username+"\",\""+titolo+"\",\""+sottotitolo+"\",\""+body+"\",\""+link+"\", \""+reward+"\")";
						boolean esito = DBManager.exDMLQuery(newPost_query);
						
						System.out.println(newPost_query);
						
						if(esito) {
							response.getWriter().write("posts.html");
						}
						else {
							//GENERIC ERROR
							response.getWriter().write("1");
						}
					}
					else {
						//INSUFFICENT BALANCE
						response.getWriter().write("2");
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			//NOT LOGGED
			response.getWriter().write("3");
		}

	}
}