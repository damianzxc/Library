package library.application.dao;

import java.util.List;
import java.util.Map;

public interface Dao<T> {

	void add(T t);
	void update(T t);
	List<T> findAll();
	T findById(String id);
	List<T> create(List<Map<String, Object>> map);

}
