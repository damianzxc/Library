package library.application.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import library.application.model.Book;
import library.application.service.BookService;

@ManagedBean
public class BookController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Book> books;
	private Book book;
	
	@ManagedProperty("#{bookService}")
	private BookService service;
	
	@PostConstruct
	public void init() {
		System.out.println("init from BookController");
		books = service.findAll();
		book = new Book();
	}
	
	public void setService(BookService service) {
		this.service = service;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<String> getStatuses() {
		return service.getStatuses();
	}
	
	public void onRowEdit(RowEditEvent event) {
		book = (Book) event.getObject();
		String message = service.updateBook(book);
		FacesMessage msg = new FacesMessage(message, book.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		init();
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Book) event.getObject()).getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		init();
	}
	
	public void addBook() {
		service.addBook(this.book);
		init();
	}
	
	public void removeBook() {
		service.removeBook(this.book);
		FacesMessage msg = new FacesMessage("Book removed", book.getId());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		init();
	}
}
