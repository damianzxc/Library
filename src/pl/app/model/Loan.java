package pl.app.model;

import java.time.LocalDate;

public class Loan {
	
	private Book book;
	private User user;
	private LocalDate rentalDate;
	private LocalDate expectedReturnDate;
	private LocalDate realReturnDate;
	
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
	public LocalDate getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}
	public LocalDate getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(LocalDate expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public LocalDate getRealReturnDate() {
		return realReturnDate;
	}
	public void setRealReturnDate(LocalDate realReturnDate) {
		this.realReturnDate = realReturnDate;
	}

}
