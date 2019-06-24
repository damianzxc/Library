package model;

public class User {

	private Integer id;
	private String name;
	private String surname;
	private Long PESEL;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Long getPESEL() {
		return PESEL;
	}
	public void setPESEL(Long pESEL) {
		PESEL = pESEL;
	}
	
	
}
