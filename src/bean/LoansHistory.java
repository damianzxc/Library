package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.model.Loan;
import library.application.service.LoanService;

@ManagedBean(name="loansHistoryBean")
public class LoansHistory implements Serializable {

	private static final long serialVersionUID = 4822084623743417247L;

	private List<Loan> allLoans;
	
	@ManagedProperty("#{loanService}")
	private LoanService service;

	@PostConstruct
	public void init() {
		allLoans = service.findAll();
	}
	
	public List<Loan> getAllLoans() {
		return allLoans;
	}

	public void setService(LoanService service) {
		this.service = service;
	}
	
	
}
