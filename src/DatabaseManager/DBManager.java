package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class execute DML and DQL query to the local server "DBNAME" in order to save / retrieve user or save / check synonyms
 * @author AlessioTranzocchi
 *
 */
public class DBManager {
	
	public static final String DBNAME = "pysDB.db";
	
	/**
	 * @param qry : The query to execute ( without return )
	 * @return if the query affected any row
	 */
	public static boolean exDMLQuery(String qry) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+DBNAME);
			Statement state = conn.createStatement();
			//Execute the query and check if the query affects at least 1 row
			boolean check = state.executeUpdate(qry) > 0;
			//CLOSE THE STATE AND THE CONNECTION
			state.close();
			conn.close();
			return check;
		
		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		
		}
	}
	
	/**
	 * Specified method to save a new user into the Database
	 * @param mail : the email of the user to register
	 * @param password : the password of the user to register
	 * @param languages : the languages and grades of the user to register : I.E. "IT,NT;EN,B1"
	 * @return if the user is registered successfully
	 */
	public static boolean exDMLQuery(double prova) {
		return exDMLQuery("INSERT INTO balance (user_balance) VALUES("+prova+");");
	}
	
	/**
	 * @param qry the query to execute ( with return )
	 * @return the result of the query
	 */
	public static ResultSet exDQLQuery(String qry) {
		ResultSet result = null;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:"+DBNAME);
			Statement state = conn.createStatement();
			//Execution of the query
			result = state.executeQuery(qry);
			//CLOSE THE STATE AND THE CONNECTION
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Specified method to get the user data given email and password
	 * @param mail : the email of the user to search
	 * @param password : the password of the user to search
	 * @return the user that match the criteria
	 */
	public static ResultSet exDQLQuery(String mail,String password) {
		//Composing of the query
		return exDQLQuery("SELECT * FROM users u WHERE u.email = \""+mail+"\" AND u.password = \""+password+"\"");
	}
	
	public static void main(String args[]) {
		
		exDMLQuery(4.20);
		
	}
	
	
}
