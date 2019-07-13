package library.application.model;

import java.util.UUID;

public abstract class BaseObject {

	private String id;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setRandomId() {
		this.id = UUID.randomUUID().toString().substring(0, 8);
	}
}
