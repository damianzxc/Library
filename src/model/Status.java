package model;

public enum Status {
	
	AVAILABLE,
	LOANED;
	
	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
