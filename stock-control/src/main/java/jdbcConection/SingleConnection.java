package jdbcConection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static Connection connection;
	
	private static String url = "jdbc:postgresql://localhost:5432/estoque";
	private static String user = "postgres";
	private static String password = "admin";
	
	static {
		connect();
	}
	
	public SingleConnection() {
		getConnection();
	}
	
	private static void connect() {
		try {
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.println("Connected!");
			}
			
		}catch(Exception e) {
			System.out.println("Connection Denied!");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
