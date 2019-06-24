package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;

import java.util.Locale.Category;

import model.Book;
import model.User;
import service.DBConnection;
import service.DBService;

@ManagedBean
public class UserDao {

	
	public List<User> findAll() {
		String query = "SELECT * FROM users";
		List<User> userList = new ArrayList<User>();
		try {
//			Connection connection = null;
//			DBConnection dbConnection = DBConnection.getInstance();
//			connection = dbConnection.getConnection();
			Connection connection = DBService.getConnection("users");
			
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				User user = new User();
				user.setId(Integer.parseInt(rs.getString("id")));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
				//user.setPESEL(Long.parseLong(rs.getString("pesel")));
				userList.add(user);
				System.out.println(user.getName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}
