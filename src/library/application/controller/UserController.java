package library.application.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import library.application.model.User;
import library.application.service.UserService;

@ManagedBean(name = "dtUserBean")
public class UserController implements Serializable{

	private static final long serialVersionUID = 3L;

	private List<User> users;
	private User user;
	
	@ManagedProperty("#{userService}")
	private UserService service;
	
	@PostConstruct
	public void init() {
		System.out.println("init from UserController");
		users = service.findAll();
		user = new User();
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		User user = new User();
		user = ((User) event.getObject());
		service.updateUser(user);
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void addUser() {
		service.addUser(user.getName(), user.getSurname(), user.getPesel());
		FacesMessage msg = new FacesMessage("New user added", user.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		init();
	}
	
	public void removeUser(String id) {

		service.removeUser(id);
		FacesMessage msg = new FacesMessage("User removed", id);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		init();
	}
}
