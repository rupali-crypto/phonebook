package phonebook.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class ContactNotFoundException extends RuntimeException {
	public ContactNotFoundException(String message) {
		
		super(message);
	}
}
