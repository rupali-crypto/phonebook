package phonebook.Exceptions;

public class ContactNotFoundException extends RuntimeException {
	public ContactNotFoundException(String message) {
		
		super(message);
	}
}
