package library.application.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.model.Loan;
import library.application.service.LoanService;

@ManagedBean(name = "loansActiveBean")
public class LoansActive implements Serializable {

	private static final long serialVersionUID = 9149322395457793193L;

	private List<Loan> activeLoans;
	
	@ManagedProperty("#{loanService}")
	private LoanService service;
	
	@PostConstruct
	public void init() {
		activeLoans = service.findAllActive();
	}

	public List<Loan> getActiveLoans() {
		return activeLoans;
	}

	public void setService(LoanService service) {
		this.service = service;
	}
	
	
}
