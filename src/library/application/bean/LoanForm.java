package library.application.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import library.application.model.Book;
import library.application.model.Status;
import library.application.service.LoanService;

@ManagedBean(name = "loanFormBean")
@SessionScoped
public class LoanForm implements Serializable {

	private static final long serialVersionUID = 4135115460759533776L;

	private List<Book> shortList;

	@ManagedProperty("#{loanService}")
	private LoanService service;

	@PostConstruct
	public void init() {
		shortList = new ArrayList<Book>();
	}

	public List<Book> getShortList() {
		return shortList;
	}

	public void setService(LoanService service) {
		this.service = service;
	}

	public void add() throws IOException {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String bookId = request.getParameter("form:input1");
		Book bookToAdd = service.findBookById(bookId);
		boolean ready = false;
		if (bookToAdd != null) {
			if (bookToAdd.getStatus().equals(Status.AVAILABLE.name())) {
				ready = true;
			}
			for (Book item : shortList) {
				if (item.getId().equals(bookToAdd.getId())) {
					ready = false;
				}
			}
			if (ready) {
				shortList.add(bookToAdd);
			}
		}
	}

	public void remove(String id) {
		for (int i = 0; i < shortList.size(); i++) {
			if (shortList.get(i).getId().equals(id)) {
				shortList.remove(i);
			}
		}
	}

	public void save() throws IOException {
		System.out.println("save");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String expectedReturnDate = request.getParameter("save:input2");
		System.out.println(expectedReturnDate);
		String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session-user");
		service.save(shortList, userId, expectedReturnDate);
		FacesContext.getCurrentInstance().getExternalContext().redirect("user-loans.xhtml");
	}

}
