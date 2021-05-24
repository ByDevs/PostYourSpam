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
@WebServlet(name="getPostServlet", urlPatterns="/getpost.jsp")
public class getPostServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;


	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String id = request.getParameter("post_id");
			String getPostQuery = "SELECT * FROM post p WHERE p.id = \""+id+"\"";
			
			System.out.println(getPostQuery);
			
			ResultSet postsRes = DBManager.exDQLQuery(getPostQuery);

			if(postsRes !=  null && postsRes.next()) {
				String username = postsRes.getString("username");
				String titolo = postsRes.getString("titolo");
				String sintesi = postsRes.getString("sintesi");
				String body = postsRes.getString("body");
				String reward = postsRes.getString("reward");
				String link = postsRes.getString("link");
				String data = postsRes.getString("data");
				String views = postsRes.getString("views");
				
				String postFormatted = body + "|" + titolo + "|" + sintesi + "|" + reward + "|"+ username + "|"+ data + "|" + views + "|" + link;
				postsRes.close();
				
				//Send post
				response.getWriter().write(postFormatted);
			}
			else {
				//No posts
				response.getWriter().write("1");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			response.getWriter().write("2");
		}
	}
}