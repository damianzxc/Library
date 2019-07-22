package library.application.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.dao.BookDao;
import library.application.dao.LoanDao;
import library.application.dao.UserDao;
import library.application.model.Book;
import library.application.model.Loan;
import library.application.model.Status;
import library.application.model.User;

@ManagedBean
@ApplicationScoped
public class LoanService {
	
	@ManagedProperty("#{loanDao}")
	private LoanDao dao;
	
	@ManagedProperty("#{bookDao}")
	private BookDao bookDao;
	
	@ManagedProperty("#{userDao}")
	private UserDao userDao;

	public void setDao(LoanDao dao) {
		this.dao = dao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

	public void save(List<Book> shortList, String userId, String expectedReturnDate) {
		if (shortList.size() > 0) {
			for (Book book : shortList) {
				Loan loan = new Loan();
				loan.setRandomId();
				User user = userDao.findById(userId);
				loan.setUser(user);
				loan.setBook(book);
				loan.setRentalDate(new Date());
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					loan.setExpectedReturnDate(format.parse(expectedReturnDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				loan.setActive(true);
				System.out.println("dodawanie");
				add(loan);
			}
		}
		
	}

	public Book findBookById(String bookId) {
		return bookDao.findById(bookId);
	}

	public List<Loan> findAllInactiveByUserId(String userId) {
		return dao.findAllInactiveByUserId(userId);
	}

	public Loan findById(String id) {
		return dao.findById(id);
	}

	public List<Loan> findAllActive() {
		return dao.findAllActive();
	}

}
