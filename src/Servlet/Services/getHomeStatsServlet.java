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
@WebServlet(name="getHomeStatsServlet", urlPatterns="/gethomestats.jsp")
public class getHomeStatsServlet extends Servlet_Base{
	
	private static final long serialVersionUID = 1L;
	
	String countUser;
	String countPost;
	String sumBalance;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countUserQuery = "SELECT COUNT(username) as users FROM Utente";
		String countPostQuery = "SELECT COUNT(ID) as posts FROM post";
		String sumBalanceQuery = "SELECT SUM(user_balance) as sum FROM balance;";
		
		try {
			ResultSet countUserResult = DBManager.exDQLQuery(countUserQuery);
			countUser = countUserResult.getString("users");
			countUserResult.close();
		
			ResultSet countPostResult = DBManager.exDQLQuery(countPostQuery);
			countPost = countPostResult.getString("posts");
			countPostResult.close();
			
			ResultSet sumBalanceResult = DBManager.exDQLQuery(sumBalanceQuery);
			sumBalance = sumBalanceResult.getString("sum");
			sumBalanceResult.close();
			
			String stats = countUser + "|" + countPost + "|" + sumBalance;
			response.getWriter().write(stats);
		
		}catch(SQLException e) {
			e.printStackTrace();
			response.getWriter().write("2");
		}
	}

}
