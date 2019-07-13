package library.application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.application.service.DBConnection;

public abstract class BaseDao {
	

	protected void execute(String query, List<Object> params) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			ps.executeUpdate();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected List<Map<String, Object>> get(String query, List<Object> params) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			if (params != null) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(i + 1, params.get(i));
				}
			}
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Map<String, Object> row = new HashMap<String, Object>();
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						row.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
					}
					result.add(row);
				}
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
