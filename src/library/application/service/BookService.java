package library.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.dao.BookDao;
import library.application.model.Book;
import library.application.model.Status;

@ManagedBean
@ApplicationScoped
public class BookService {

	@ManagedProperty("#{bookDao}")
	private BookDao dao;
	
	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	public List<Book> findAll() {
		return dao.findAll();
	}

	public List<String> getStatuses() {
		List<String> statuses = new ArrayList<String>();
		statuses.add(Status.AVAILABLE.name());
		statuses.add(Status.NOT_AVAILABLE.name());
		return statuses;
	}

	public String updateBook(Book book) {
		if (book.getStatus().equals(Status.AVAILABLE.name())) {
			if (book.getActive() == false) {
				book.setStatus(Status.NOT_AVAILABLE.name());
			}
			dao.update(book);
			return "Dokonano edycji";
		} else {
			return "Edycja niemożliwa";
		}
	}

	public void addBook(Book book) {
		book.setRandomId();
		book.setStatus(Status.AVAILABLE.name());
		book.setActive(true);
		dao.add(book);
	}
	
	public void removeBook(Book book) {
		//dao.removeBook(book.getId());
	}

	public Book findBookById(String id) {
		return dao.findById(id);
	}

	
}
