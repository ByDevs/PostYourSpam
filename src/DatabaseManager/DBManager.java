package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class execute DML and DQL query to the local server "DBNAME" in order to save / retrieve user or save / check synonyms
 * @author Alessio Tranzocchi & Matteo Catalano
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
	
	
	
}
