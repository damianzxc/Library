package library.application.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import library.application.model.User;

@ManagedBean(name = "userDao")
@ApplicationScoped
public class UserDao extends BaseDao implements Dao<User> {

	@Override
	public void add(User user) {
		String query = "INSERT INTO USERS (ID, NAME, SURNAME, PESEL, ACTIVE) VALUES (?, ?, ?, ?, ?);";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getId());
		params.add(user.getName());
		params.add(user.getSurname());
		params.add(user.getPesel());
		params.add(user.getActive());
		super.execute(query, params);
	}

	@Override
	public void update(User user) {
		String query = "UPDATE USERS SET NAME=?, SURNAME=?, PESEL=?, ACTIVE=? WHERE ID=?;";
		List<Object> params = new ArrayList<Object>();
		params.add(user.getName());
		params.add(user.getSurname());
		params.add(user.getPesel());
		params.add(user.getActive());
		params.add(user.getId());
		super.execute(query, params);
	}

	@Override
	public List<User> findAll() {
		String query = "SELECT * FROM USERS";
		List<User> list = create(super.get(query, null));
		return list;
	}

	@Override
	public User findById(String id) {
		String query = "SELECT * FROM USERS WHERE ID LIKE ?;";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<User> users = create(super.get(query, params));
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> create(List<Map<String, Object>> map) {
		List<User> users = new ArrayList<User>();
		if (map.size() > 0) {
			for (Map<String, Object> row : map) {
				User user = new User();
				user.setId((String) row.get("id"));
				user.setName((String) row.get("name"));
				user.setSurname((String) row.get("surname"));
				user.setPesel((String) row.get("pesel"));
				user.setActive((Boolean) row.get("active"));
				users.add(user);
			}
		}
		return users;
	}

	public User findUserByPesel(String pesel) {
		String query = "SELECT * FROM USERS WHERE PESEL LIKE ?;";
		List<Object> params = new ArrayList<Object>();
		params.add(pesel);
		List<User> users = create(super.get(query, params));
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
