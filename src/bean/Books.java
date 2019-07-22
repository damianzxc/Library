package bean;

import java.io.IOException;
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

@ManagedBean(name = "booksBean")
public class Books implements Serializable {

	private static final long serialVersionUID = 4706009594400864320L;
	
	private Book book;
	private List<Book> books;
	
	@ManagedProperty("#{bookService}")
	private BookService service;
	
	public void setService(BookService service) {
		this.service = service;
	}

	@PostConstruct
	public void init() {
		book = new Book();
		books = service.findAll();
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBooks() {
		return books;
	}
	
	public void onRowEdit(RowEditEvent event) throws InterruptedException, IOException {
		book = (Book) event.getObject();
		String message = service.updateBook(book);
		FacesMessage msg = new FacesMessage(message, book.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Anulowano", ((Book) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
