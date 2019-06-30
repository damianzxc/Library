package pl.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pl.app.model.User;
import pl.app.service.DBConnection;

public class UserDao {
	
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		String query = "SELECT * FROM users;";
		
		try {
			DBConnection dbConnection = DBConnection.getInstance();
			Connection connection = dbConnection.getConnection();
			
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				user.setPesel(rs.getString("pesel"));
				users.add(user);
			}
			rs.close();
			System.out.println("Records readed successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public void addUser(User user) {
		String query = "INSERT INTO USERS (ID, NAME, SURNAME, PESEL) VALUES (?, ?, ?, ?)";
		
		try {
			DBConnection dbConnection = DBConnection.getInstance();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSurname());
			ps.setString(4, user.getPesel());
			ps.executeUpdate();
			ps.close();
			System.out.println("User added successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		String query = "UPDATE USERS SET NAME=?, SURNAME=?, PESEL=? WHERE ID=?";
		
		try {
			DBConnection dbConnection = DBConnection.getInstance();
			Connection connection = dbConnection.getConnection();
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getSurname());
			ps.setString(3, user.getPesel());
			ps.setString(4, user.getId());
			ps.executeUpdate();
			ps.close();
			System.out.println("User updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
