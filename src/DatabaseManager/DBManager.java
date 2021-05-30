package DatabaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+DBNAME);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * @param querys the list of querys to execute in the transaction
	 * @return if the transaction executed succesfully
	 */
	public static boolean performTransaction(String... querys) {
		Connection con = DBManager.getConnection();
		try {
			con.setAutoCommit(false);
			List<PreparedStatement> statements = new ArrayList<>();
			
			for(String x:querys)
				statements.add(con.prepareStatement(x));
			
			for(PreparedStatement st:statements)
				st.executeUpdate();
		
			con.commit();
			return true;
		} catch (SQLException e) {
			try {
				con.rollback();
				return false;
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		}
	}
}