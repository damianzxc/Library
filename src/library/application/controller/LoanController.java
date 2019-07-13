package library.application.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import library.application.model.Book;
import library.application.model.Loan;
import library.application.model.User;
import library.application.service.BookService;
import library.application.service.LoanService;
import library.application.service.UserService;

@ManagedBean(name = "loanController")
@RequestScoped
public class LoanController implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private User user;
	private Book book;
	private Loan loan;
	private List<Loan> userLoans;
	private List<Loan> allLoans;
	
	@ManagedProperty("#{loanService}")
	private LoanService service;

	@ManagedProperty("#{userService}")
	private UserService userService;
	
	@ManagedProperty("#{bookService}")
	private BookService bookService;
	
	@PostConstruct
	public void init() {
		System.out.println("init from LoanController");
		this.user = new User();
		findSessionUser();
		this.book = new Book();
		this.loan = new Loan();
		this.allLoans = service.findAll();
		this.userLoans = service.findAllActiveByUserId(user.getId());
	}
	
	public void setService(LoanService service) {
		this.service = service;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public List<Loan> getUserLoans() {
		return userLoans;
	}
	
	public List<Loan> getAllLoans() {
		return allLoans;
	}

	public void findUserByPesel() throws IOException {
		user = userService.findUserByPesel(user.getPesel());
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().clear();
		if(user != null && user.getActive() == true) {
			context.getExternalContext().getSessionMap().put("session-user", user.getId());
			System.out.println("dodano użytkownika do sesji");
			context.getExternalContext().redirect("loan-form.xhtml");
		} else {
			FacesMessage msg = new FacesMessage("Nie znaleziono użytkownika", "Lub użytkownik nieaktywny");
			context.addMessage(null, msg);
		}

	}
	
	private void findSessionUser() {
		Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		String id = (String) map.get("session-user");
		if (id != null ) {
			user = userService.findUserById(id);
		}

	}
	
	public void newLoan() {
		book = bookService.findBookById(book.getId());
		boolean ready = false;
		if(book.getStatus().equals("AVAILABLE") && book.getActive() == true) {
			ready = true;
		}
		for (Loan l : userLoans) {
			if(l.getBook().getId().equals(book.getId())) {
				ready = false;
			}
		}
		if(ready) {
			loan.setRandomId();
			loan.setRentalDate(new java.util.Date());
			loan.setActive(true);
			loan.setUser(user);
			loan.setBook(book);
			service.add(loan);
			init();
			System.out.println(book.getId());
		}
		
	}
	
	public void newReturn() {
		for (Loan loan : userLoans) {
			if (loan.getBook().getId().equals(book.getId())) {
				service.returnBook(loan);
				init();
				System.out.println("New return success");
			} else {
				System.out.println("return fail");
			}
		}
		
	}
 	
}
