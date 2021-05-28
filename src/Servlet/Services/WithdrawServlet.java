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
 * Servlet that handle the request of all posts by a user
 * @author Alessio Tranzocchi & Matteo Catalano
 *
 */
@WebServlet(name="WithdrawServlet", urlPatterns="/withdraw.jsp")
public class WithdrawServlet extends Servlet_Base{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String wallet;
		String quantity;
		String username = "";
		String balance;
		double userBalance;
		
		try {
			String email = (String) session.getAttribute("logged");
			
			//check if user is logged
			if(email != null) {
				wallet = request.getParameter("wallet");
				quantity = request.getParameter("quantity");
				
				//search username and balance
				String usernameQuery = "SELECT username FROM utente u WHERE u.email = '" + email + "'";
				String usrQuery = "SELECT b.user_balance, u.Username FROM balance b, utente u WHERE b.user_id = (" + usernameQuery + ") and u.email = \"" + email + "\"";
				ResultSet usrRes = DBManager.exDQLQuery(usrQuery);
				if(usrRes != null) {
					username = usrRes.getString("username");
					balance = usrRes.getString("user_balance");
					usrRes.close();
					//check if quantity > userBalance
					userBalance = Double.parseDouble(balance);
					double withdrawQ = Double.parseDouble(quantity);
					if(withdrawQ <= userBalance) {
						//update user balance
						userBalance -= withdrawQ;
						String updateBalanceQuery = "UPDATE BALANCE SET user_balance = '" + userBalance + "' WHERE user_id = '" + username + "'";
						boolean successUpdate = DBManager.exDMLQuery(updateBalanceQuery);
						//generate log
						if(successUpdate) {
							String newLogQuery = "INSERT INTO balance_log (balance_id,log_text,amount) VALUES ('" + username + "', 'Ritiro','-"+ quantity +"')";
							boolean successQuery = DBManager.exDMLQuery(newLogQuery);
							if(!successQuery) {
								response.getWriter().write("2");
							}
						}
					}
					
				}
			}
			else {
				//user not logged
				response.getWriter().write("1");
			}
		}catch(SQLException e) {
			response.getWriter().write("2");
			e.printStackTrace();
		}
		
	}
}
