package library.application.model;

import java.util.Date;


public class Loan extends BaseObject{
	
	private Book book;
	private User user;
	private Date rentalDate;
	private Date expectedReturnDate;
	private Date realReturnDate;
	private Boolean active;
	

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public Date getRealReturnDate() {
		return realReturnDate;
	}
	public void setRealReturnDate(Date realReturnDate) {
		this.realReturnDate = realReturnDate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
