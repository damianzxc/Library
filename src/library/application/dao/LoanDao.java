package library.application.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import library.application.model.Loan;

@ManagedBean
@ApplicationScoped
public class LoanDao extends BaseDao implements Dao<Loan> {
	
	
	
	@Override
	public void add(Loan loan) {
		String query = "INSERT INTO LOANS (ID, BOOK_ID, USER_ID, RENTAL_DATE, EXPECTED_RETURN_DATE, ACTIVE) VALUES (?, ?, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.add(loan.getId());
		params.add(loan.getBook().getId());
		params.add(loan.getUser().getId());
		params.add(java.sql.Date.valueOf(dateFormat.format(loan.getRentalDate())));
		params.add(java.sql.Date.valueOf(dateFormat.format(loan.getExpectedReturnDate())));
		params.add(loan.getActive());
		super.execute(query, params);
		
	}

	@Override
	public void update(Loan t) {
	}
	
	@Override
	public List<Loan> findAll() {
		String query = "SELECT * FROM LOANS";
		List<Loan> loans = create(super.get(query, null));
		return loans;
	}

	@Override
	public Loan findById(String id) {
		String query = "SELECT * FROM LOANS WHERE ID LIKE ?;";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Loan> loans = create(super.get(query, params));
		if (loans.size() > 0) {
			return loans.get(0);
		}
		return null;
	}

	@Override
	public List<Loan> create(List<Map<String, Object>> map) {
		List<Loan> loans = new ArrayList<Loan>();
		if (map.size() > 0) {
			for (Map<String, Object> row : map) {
				Loan loan = new Loan();
				UserDao userDao = new UserDao();
				BookDao bookDao = new BookDao();
				loan.setId((String) row.get("id"));
				loan.setBook(bookDao.findById((String) row.get("book_id")));
				loan.setUser(userDao.findById((String) row.get("user_id")));
				loan.setRentalDate((Date) row.get("rental_date"));
				loan.setExpectedReturnDate((Date) row.get("expected_return_date"));
				loan.setRealReturnDate((Date) row.get("real_return_date"));
				loan.setActive((Boolean) row.get("active"));
				loans.add(loan);
			}
		}
		return loans;
	}


	public List<Loan> findAllByUserId(String id) {
		String query = "SELECT * FROM LOANS WHERE USER_ID LIKE ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Loan> loans = create(super.get(query, params));
		return loans;
	}


	public List<Loan> findAllActiveByUserId(String id) {
		String query = "SELECT * FROM LOANS WHERE USER_ID LIKE ? AND ACTIVE = TRUE;";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Loan> loans = create(super.get(query, params));
		return loans;
	}

	public void returnBook(Loan loan) {
		String query = "UPDATE LOANS SET ACTIVE = FALSE, REAL_RETURN_DATE = ? WHERE BOOK_ID LIKE ?;";
		List<Object> params = new ArrayList<Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.add(java.sql.Date.valueOf(dateFormat.format(new Date())));
		params.add(loan.getBook().getId());
		super.execute(query, params);
		
	}

	public void setBookStatus(String status, String id) {
		String query = "UPDATE BOOKS SET STATUS = ? WHERE ID LIKE ?";
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(id);
		super.execute(query, params);
	}

	public List<Loan> findAllInactiveByUserId(String userId) {
		String query = "SELECT * FROM LOANS WHERE ACTIVE = FALSE AND USER_ID LIKE ?;";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		List<Loan> loans = create(super.get(query, params));
		return loans;
	}

	public List<Loan> findAllActive() {
		String query = "SELECT * FROM LOANS WHERE ACTIVE = TRUE";
		List<Loan> loans = create(super.get(query, null));
		return loans;
	}

	
}
