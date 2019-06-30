package pl.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pl.app.dao.UserDao;
import pl.app.model.User;

@ManagedBean(name = "userService")
@ApplicationScoped
public class UserService {
	
	public List<User> findAll() {
		UserDao userDao = new UserDao();
		return userDao.findAll();
	}

}
