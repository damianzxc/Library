package library.application.service;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.dao.LoanDao;
import library.application.model.Loan;
import library.application.model.Status;

@ManagedBean
@ApplicationScoped
public class LoanService {
	
	@ManagedProperty("#{loanDao}")
	private LoanDao dao;

	public void setDao(LoanDao dao) {
		this.dao = dao;
	}

	public List<Loan> findAllByUserId(String id) {
		return dao.findAllByUserId(id);
	}

	public void add(Loan loan) {
		dao.add(loan);
		dao.setBookStatus(Status.NOT_AVAILABLE.name(), loan.getBook().getId());
	}

	public List<Loan> findAllActiveByUserId(String id) {
		return dao.findAllActiveByUserId(id);
	}

	public void returnBook(Loan loan) {
		dao.returnBook(loan);
		dao.setBookStatus(Status.AVAILABLE.name(), loan.getBook().getId());
	}

	public List<Loan> findAll() {
		return dao.findAll();
	}


}
