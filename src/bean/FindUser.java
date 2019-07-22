package bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import library.application.model.User;
import library.application.service.UserService;

@ManagedBean(name = "findUserBean")
public class FindUser implements Serializable {

	private static final long serialVersionUID = 4696291817992912046L;

	private String pesel;
	private User sessionUser;

	@PostConstruct
	public void init() {
		System.out.println("init from Find User Bean");
		findSessionUser();
	}

	@ManagedProperty(value = "#{userService}")
	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}

	public User getSessionUser() {
		return sessionUser;
	}
	
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public void putSessionUser() throws IOException {
		User user = service.findUserByPesel(pesel);
		FacesContext context = FacesContext.getCurrentInstance();
		if (user != null) {
			if (user.getActive() == true) {
				context.getExternalContext().getSessionMap().clear();
				context.getExternalContext().getSessionMap().put("session-user", user.getId());
				context.getExternalContext().redirect("loan-form.xhtml");
			}
			if (user.getActive() == false || user.getActive() == null) {
				FacesMessage msg = new FacesMessage("Użytkownik nieaktywny", user.getName() + " " + user.getSurname());
				context.addMessage(null, msg);
			}
		} else {
			context.addMessage(null, new FacesMessage("Nie znaleziono użytkownika"));
		}
	}

	private void findSessionUser() {
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String id = (String) map.get("session-user");
		if (id != null) {
			sessionUser = service.findUserById(id);
		}
	}
	
	public void clearSession() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
		FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
	}

}
