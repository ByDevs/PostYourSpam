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
@WebServlet(name="getFreeServlet", urlPatterns="/getfree.jsp")
public class getFreeServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;

	private static final double rewardForDaily = 0.5;


	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String email = "";
		if(session.getAttribute("logged") == null) {
			response.getWriter().write("1");
			return;
		}
		else {
			email = (String) session.getAttribute("logged");
			String usernameQuery = "SELECT username FROM utente u WHERE u.email = \""+email+"\"";
			if(request.getParameter("req_type").equals("remaining_time")) {


				String remTimeQuery = "SELECT strftime('%s',(datetime('now', 'localtime'))) - strftime('%s',b.data) as difference\n" + 
						" FROM balance_log b\n" + 
						" WHERE b.balance_id = ("+usernameQuery+") AND b.log_text = \"DAILY_REWARD\" ORDER BY b.data DESC LIMIT 1";

				try {
					ResultSet remTimeRes = DBManager.exDQLQuery(remTimeQuery);

					if(remTimeRes!=null) { 

						int n1 = (int) (Math.random() * 10);
						int n2 = (int) (Math.random() * 10);

						String domanda = "Quanto fa "+ n1 +" + "+ n2 + " ?";

						//Saving the answer
						session.setAttribute("answer", "" + (n1 + n2));

						JSONBuilder builder = new JSONBuilder();
						builder.append("domanda", domanda);


						if(remTimeRes.next()) {
							int seconds = Integer.parseInt(remTimeRes.getString("difference"));

							if(seconds >= 86400) {
								builder.append("message", "CLAIM");
								response.getWriter().write(builder.build());
							}
							else {
								builder.append("message", "WAIT");
								builder.append("seconds", "" + (86400 - seconds));
								response.getWriter().write(builder.build());
							}

						}
						else {
							builder.append("message", "CLAIM");
							response.getWriter().write(builder.build());
						}

						remTimeRes.close();
					}
					else
						//Generic error
						response.getWriter().write("2");

				}catch(SQLException e) {
					//Generic error
					response.getWriter().write("2");
					e.printStackTrace();
				}
			}
			else if(request.getParameter("req_type").equals("claim_reward")){

				//Check the captcha

				int answer = Integer.parseInt(request.getParameter("answer"));

				if(answer == Integer.parseInt( (String) session.getAttribute("answer"))) {
					String username = "";

					try {

						ResultSet usrRes = DBManager.exDQLQuery(usernameQuery);
						

						if(usrRes != null && usrRes.next()) {
							username = usrRes.getString("username");
							usrRes.close();
						}

						//Preparing statements
						String update1 = "UPDATE balance " + 
								"SET user_balance = (SELECT user_balance FROM balance WHERE user_id = \""+username+"\") + " + rewardForDaily +
								" WHERE user_id = \""+ username +"\"";

						String newLogUser = "INSERT INTO balance_log (balance_id,log_text,amount) VALUES (\""+username+"\",\"DAILY_REWARD\",\""+ rewardForDaily +"\")";

						DBManager.performTransaction(update1,newLogUser);

						//Generic error
						response.getWriter().write("ok");

					}catch(SQLException e) {
						//Generic error
						response.getWriter().write("2");
						e.printStackTrace();
					}
				}
				else {
					//Wrong captcha
					response.getWriter().write("wrong");
				}
			}
		}
	}
}