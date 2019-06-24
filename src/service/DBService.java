package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
	private static String url = "jdbc:postgresql://localhost:5432/library-test";
	private static String username = "postgres";
	private static String password = "admin";

	public static Connection getConnection(String database) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
