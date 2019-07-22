package library.application.bean;

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

@ManagedBean(name = "usersBean")
public class Users implements Serializable {

	private static final long serialVersionUID = 4284572555216108008L;

	private List<User> users;
	
	@ManagedProperty("#{userService}")
	private UserService service;
	
	@PostConstruct
	public void init() {
		users = service.findAll();
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void onRowEdit(RowEditEvent event) {
		User edited = (User) event.getObject();
		service.updateUser(edited);
		FacesMessage msg = new FacesMessage("Dokonano edycji", edited.getName() +" "+ edited.getSurname());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Anulowano");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
