package library.application.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import library.application.model.User;
import library.application.service.UserService;

@ManagedBean(name = "userFormBean")
public class UserForm implements Serializable {

	private static final long serialVersionUID = 6494488956802335702L;

	private User user;
	
	@PostConstruct
	public void init() {
		user = new User();
	}
	
	@ManagedProperty(value = "#{userService}")
	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addUser() throws IOException {
		service.addUser(user.getName(), user.getSurname(), user.getPesel());
		FacesContext.getCurrentInstance().getExternalContext().redirect("users.xhtml");
	}
	
}
