package library.application.model;

public enum Status {

	AVAILABLE, NOT_AVAILABLE;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}

}
