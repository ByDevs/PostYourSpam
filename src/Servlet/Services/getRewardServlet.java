package Servlet.Services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
@WebServlet(name="getRewardServlet", urlPatterns="/getreward.jsp")
public class getRewardServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;


	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String username = "";
		String email = "";

		//Check if logged in the session
		if(session.getAttribute("logged") != null)
			email = (String) session.getAttribute("logged");
		else {
			//Not logged
			response.getWriter().write("3");
			return;
		}
		try {

			//Get the username
			String getRequestUser = "SELECT u.Username FROM Utente u WHERE u.email = \""+ email + "\"";
			ResultSet userRes = DBManager.exDQLQuery(getRequestUser);

			if(userRes !=  null && userRes.next()) {
				username = userRes.getString("Username");
				userRes.close();
			}
			else {
				//Not logged
				response.getWriter().write("3");
				return;
			}
			String id = request.getParameter("post_id");
			// Check if the creator of the post has enough balance is not the user that requested the request and 
			String getPostCreatorQuery = "SELECT p.username, p.reward FROM post p, balance b, Utente u WHERE p.id = \""+id+"\" and"
					+ " b.user_id = p.username AND b.user_balance >= p.reward "
					+ "AND b.user_id = u.Username and u.email != \"" + email + "\"";

			ResultSet postsRes = DBManager.exDQLQuery(getPostCreatorQuery);

			if(postsRes !=  null && postsRes.next()) {

				//GENERATE SQL STATEMENTS
				String usernameCreator = postsRes.getString("username");
				String reward = postsRes.getString("reward");
				postsRes.close();
				String logText = "POST_REWARD-"+id;


				//Check if the user already clicked on post
				String alreadyDoneQuery = "SELECT * FROM balance_log WHERE log_text = \""+logText+"\" AND balance_id = \""+username+"\"";
				ResultSet doneRes = DBManager.exDQLQuery(alreadyDoneQuery);
				if(doneRes != null && doneRes.next()) {
					//Already clicked
					response.getWriter().write("4");
					doneRes.close();
				}
				else {
					doneRes.close();
					String update1 = "UPDATE balance " + 
							"SET user_balance = (SELECT user_balance FROM balance WHERE user_id = \""+usernameCreator+"\") - "+reward+
							" WHERE user_id = \""+ usernameCreator +"\"";
					String update2 = "UPDATE balance " + 
							"SET user_balance = (SELECT user_balance FROM balance WHERE user_id = \""+username+"\") + "+reward+
							" WHERE user_id = \""+ username +"\"";
					String newLogUser = "INSERT INTO balance_log (balance_id,log_text,amount) VALUES (\""+username+"\",\""+ logText +"\",\""+ reward +"\")";
					String newLogCreator = "INSERT INTO balance_log (balance_id,log_text,amount) VALUES (\""+usernameCreator+"\",\""+ logText +"\",\""+ "-" + reward +"\")";
					String updatePostViews = "UPDATE post SET views = (SELECT views FROM post x WHERE x.ID = \""+id+"\") +1 WHERE id = \""+id+"\"";
					
					System.out.println("start transaction");
					//START TRANSACTION
					
					if(DBManager.performTransaction(update1,update2,newLogUser,newLogCreator,updatePostViews))
						response.getWriter().write("ok");

					//END TRANSACTION
					System.out.println("end transaction");
				}
			}
			else {
				//No balance
				response.getWriter().write("1");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			response.getWriter().write("2");
		}
	}
}