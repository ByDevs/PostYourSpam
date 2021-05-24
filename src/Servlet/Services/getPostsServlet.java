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
 * @author AlessioTranzocchi
 *
 */
@WebServlet(name="getPostsServlet", urlPatterns="/getposts.jsp")
public class getPostsServlet extends Servlet_Base {
	private static final long serialVersionUID = 8484501789787L;


	@Override
	protected void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String getPostsQuery = "SELECT * FROM post p Order By p.data DESC";
			
			ResultSet postsRes = DBManager.exDQLQuery(getPostsQuery);
			List<String> posts = new ArrayList<>();
			
			if(postsRes !=  null) {
				while(postsRes.next()) {
					
					String postid = postsRes.getString("ID");
					String username = postsRes.getString("username");
					String titolo = postsRes.getString("titolo");
					String sintesi = postsRes.getString("sintesi");
					String reward = postsRes.getString("reward");
					String data = postsRes.getString("data");
					String views = postsRes.getString("views");
					
					String postFormatted = postid + "|" + titolo + "|" + sintesi + "|" + reward + "|"+ username + "|"+ data + "|" + views;
					
					posts.add(postFormatted);
				}
				postsRes.close();
				
				JSONBuilder builder = new JSONBuilder();
				
				builder.append("npost", "" + posts.size());
				
				for(int i = 0;i < posts.size() ; i++) {
					builder.append("" + i, posts.get(i));
				}
				
				response.getWriter().write(builder.build());
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