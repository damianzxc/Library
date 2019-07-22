package library.application.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import library.application.model.Loan;
import library.application.service.LoanService;

@ManagedBean(name = "userLoansHistoryBean")
public class UserLoansHistory implements Serializable {

	private static final long serialVersionUID = 7874022732563926144L;

	private List<Loan> loans;
	
	@ManagedProperty("#{loanService}")
	private LoanService service;
	
	@PostConstruct
	public void init() {
		String userId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session-user");
		loans = service.findAllInactiveByUserId(userId);
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setService(LoanService service) {
		this.service = service;
	}
	
	
}
