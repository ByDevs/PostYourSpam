package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The base servlet where the request and the response is handled
 * @author Tranzocchi Alessio and Matteo Catalano
 *
 */
public abstract class Servlet_Base extends HttpServlet {
	
	private static final long serialVersionUID = 6784574842574L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doSomething(request, response);
	}

	/**
	 * Method that implements the logic of the back-end
	 * */
	protected abstract void doSomething(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
}
