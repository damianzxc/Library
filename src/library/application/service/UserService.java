package library.application.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.dao.UserDao;
import library.application.model.User;

@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {

	@ManagedProperty("#{userDao}")
	private UserDao dao;

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public List<User> findAll() {
		return dao.findAll();
	}

	public void updateUser(User user) {
		dao.update(user);
	}

	public void addUser(String name, String surname, String pesel) {
		User user = new User();
		user.setRandomId();
		user.setName(name);
		user.setSurname(surname);
		user.setPesel(pesel);
		user.setActive(true);
		dao.add(user);
	}

	public void removeUser(String id) {
		//dao.removeUser(id);
		System.out.println("removed");
	}


	public User findUserByPesel(String pesel) {
		User user = new User();
		user = dao.findUserByPesel(pesel);
		return user;
	}

	public User findUserById(String id) {
		return dao.findById(id);
	}

}
