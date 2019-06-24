package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance;
	private Connection conn;
	private String url = "jdbc:postgresql://localhost:5432/library-test";
	private String username = "postgres";
	private String password = "admin";

	private DBConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
			this.conn = DriverManager.getConnection(url, username, password);
		} 
		catch (ClassNotFoundException ex) {
			System.out.println("Database Connection Creation Failed : " + ex.getMessage());
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public static DBConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DBConnection();
		} 
		else if (instance.getConnection().isClosed()) {
			instance = new DBConnection();
		}
		return instance;
	}

}
