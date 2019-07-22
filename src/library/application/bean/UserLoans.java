package library.application.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import library.application.model.Loan;
import library.application.service.LoanService;

@ManagedBean(name = "userLoansBean")
public class UserLoans implements Serializable {

	private static final long serialVersionUID = 6278261911596190826L;

	private List<Loan> loans;
	
	@ManagedProperty("#{loanService}")
	private LoanService service;

	@PostConstruct
	public void init() {
		String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session-user");
		loans = service.findAllActiveByUserId(userId);
	}
	
	public void setService(LoanService service) {
		this.service = service;
	}

	public List<Loan> getLoans() {
		return loans;
	}
	
	public void remove(String id) {
		Loan loan = service.findById(id);
		service.returnBook(loan);
		init();
	}

}
