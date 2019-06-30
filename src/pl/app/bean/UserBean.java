package pl.app.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import pl.app.dao.UserDao;
import pl.app.model.User;
import pl.app.service.UserService;

@ManagedBean(name = "dtUserBean")
@ViewScoped
public class UserBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<User> users;
	
	@ManagedProperty("#{userService}")
	private UserService service;
	
	@PostConstruct
	public void init() {
		System.out.println("init");
		users = service.findAll();
	}
	
	public void setService(UserService service) {
		this.service = service;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		User user = new User();
		user = ((User) event.getObject());
		
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
