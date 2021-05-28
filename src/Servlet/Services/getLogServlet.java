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

@WebServlet(name="getLogServlet", urlPatterns="/getlogs.jsp")
public class getLogServlet extends Servlet_Base {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("logged");
		if(email != null) {
			try {
			String usernameQuery = "SELECT username FROM utente where email='" + email + "'";
			String logQuery = "SELECT * FROM balance_log bl WHERE bl.balance_id =(" + usernameQuery + ") ORDER BY data DESC";
			ResultSet logResponse = DBManager.exDQLQuery(logQuery);
			List<String> logs = new ArrayList<>();
			if(logResponse != null) {
				while(logResponse.next()) {
					String amount = logResponse.getString("amount");
					String text = logResponse.getString("log_text");
					String date = logResponse.getString("data");
					
					String log = amount + "|" + text + "|" + date;
					logs.add(log);
				}
				JSONBuilder builder = new JSONBuilder();
				builder.append("nlog", "" + logs.size());
				
				for(int i = 0;i < logs.size() ; i++) {
					builder.append("" + i, logs.get(i));				
				}
				
				response.getWriter().write(builder.build());
				logResponse.close();
			}
			}catch(SQLException e) {
				response.getWriter().write("2");
				e.printStackTrace();
			}
		}
		else {
			// 0 logs
			response.getWriter().write("1");
		}
	}
	
}
