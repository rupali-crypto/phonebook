package phonebook.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	//handle contact not found exception
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<?>handleContactNotFoundException(ContactNotFoundException exception,WebRequest request)

	{
		
		ErrorDetails errordetails=new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
	}
	
	//handle user not found exception
		@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<?>handleUserNotFoundException(UserNotFoundException exception,WebRequest request)

		{
			
			ErrorDetails errordetails=new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity(errordetails,HttpStatus.NOT_FOUND);
		}
	
	//handle global exception
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?>handleGlobalException(Exception exception,WebRequest request)

		{
			String error;
			if(exception.getMessage().contains("Error while committing the transaction"))
					 error="Please enter valid contact number";
			else
				error=exception.getMessage();
			ErrorDetails errordetails=new ErrorDetails(new Date(), error, request.getDescription(false));
			return new ResponseEntity(errordetails,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
}
