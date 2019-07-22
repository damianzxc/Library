package library.application.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import library.application.model.Book;
import library.application.service.BookService;

@ManagedBean(name = "bookFormBean")
public class BookForm implements Serializable {

	private static final long serialVersionUID = -8439725924234580551L;

	private Book book;
	
	@PostConstruct
	public void init() {
		book = new Book();
	}
	
	@ManagedProperty("#{bookService}")
	private BookService service;

	public void setService(BookService service) {
		this.service = service;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public void addBook() {
		service.addBook(book);
	}
	
}
