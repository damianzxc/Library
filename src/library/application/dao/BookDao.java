package library.application.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import library.application.model.Book;

@ManagedBean
@ApplicationScoped
public class BookDao extends BaseDao implements Dao<Book>{


	@Override
	public void add(Book book) {
		String query = "INSERT INTO BOOKS (ID, NAME, DESCRIBE, AUTHOR, PUBLISHER, RELEASE_DATE, STATUS, ACTIVE) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		List<Object> params = new ArrayList<Object>();
		params.add(book.getId());
		params.add(book.getName());
		params.add(book.getDescribe());
		params.add(book.getAuthor());
		params.add(book.getPublisher());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		params.add(java.sql.Date.valueOf(dateFormat.format(book.getReleaseDate())));
		params.add(book.getStatus());
		params.add(book.getActive());
		super.execute(query, params);
		
	}

	@Override
	public void update(Book book) {
		String query = "UPDATE BOOKS SET NAME = ?, DESCRIBE = ?, AUTHOR = ?, PUBLISHER = ?, RELEASE_DATE = ?, STATUS = ?, ACTIVE = ? WHERE ID LIKE ? ;";
		List<Object> params = new ArrayList<Object>();
		params.add(book.getName());
		params.add(book.getDescribe());
		params.add(book.getAuthor());
		params.add(book.getPublisher());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(book.getReleaseDate());
		params.add(java.sql.Date.valueOf(date));
		params.add(book.getStatus());
		params.add(book.getActive());
		params.add(book.getId());
		super.execute(query, params);
	}

	@Override
	public List<Book> findAll() {
		String query = "SELECT * FROM BOOKS;";
		List<Book> books = create(super.get(query, null));
		return books;
	}

	@Override
	public Book findById(String id) {
		String query = "SELECT * FROM BOOKS WHERE ID LIKE ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Book> books = create(super.get(query, params));
		if (books.size() > 0) {
			return books.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Book> create(List<Map<String, Object>> map) {
		List<Book> books = new ArrayList<Book>();
		if (map.size() > 0) {
			for (Map<String, Object> row : map) {
				Book book = new Book();
				book.setId((String) row.get("id"));
				book.setName((String) row.get("name"));
				book.setDescribe((String) row.get("describe"));
				book.setAuthor((String) row.get("author"));
				book.setPublisher((String) row.get("publisher"));
				book.setReleaseDate((Date) row.get("release_date"));
				book.setStatus((String) row.get("status"));
				book.setActive((Boolean) row.get("active"));
				books.add(book);
			}
		}
		return books;
	}

	public Book findOneById(String id) {
		String query = "SELECT * FROM BOOKS WHERE ID LIKE ?";
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		List<Book> books = create(super.get(query, params));
		return books.get(0);
	}


}
