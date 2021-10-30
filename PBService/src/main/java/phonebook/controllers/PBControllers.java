package phonebook.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

import phonebook.Exceptions.ContactNotFoundException;
import phonebook.Exceptions.UserNotFoundException;
import phonebook.config.SwaggerConfig;
import phonebook.repositories.ContactRepository;
import phonebook.users.Users;
@RestController
@RequestMapping("/phonebook/api")
@Validated
public class PBControllers {

	@Autowired
	ContactRepository repository;


	@RequestMapping(value = "/hello",method=RequestMethod.GET)
	
	public String getHelloWorld() {
		String message = "Hello";
		return message;
	}

	@RequestMapping(value = "/GetAllContacts", method = RequestMethod.GET)
	
	public Iterable<Users> getAllEntries() {

		Iterable<Users> phonebookCollection = repository.findAll();
		return phonebookCollection;

	}

	@RequestMapping(value = "/GetContact", method = RequestMethod.GET, params = { "contactnumber" },name = "Get a contact")
	ResponseEntity<Users> getUser(@RequestParam(value = "contactnumber") String ContactNumber) {
		Users user = new Users();
		
		user= repository.findById(Integer.parseInt(ContactNumber))
		
		.orElseThrow(()-> new ContactNotFoundException("User not found with this contact number: "+ContactNumber));
        
        return ResponseEntity.ok().body(user);
		
	}

	@RequestMapping(value = "/AddContact", method = RequestMethod.POST)
	public Users AddContact(@RequestParam(value = "name") String Name,
			@RequestParam(value = "lastname") String LastName,
			@RequestParam(value = "contactnumber") String ContactNumber) {
		Users newuser = new Users(Name, LastName, ContactNumber);
		repository.save(newuser);
		return newuser;
	}

	@RequestMapping(value = "/DeleteContact", method = RequestMethod.DELETE)
	public Users DeleteContact(@RequestParam(value = "Id") Integer Id) {
		Users usertoDelete = new Users();
		usertoDelete= repository.findById(Id).orElseThrow(()-> new UserNotFoundException("User not found with this Id: "+Id));
	
			repository.deleteById(usertoDelete.getId());

			return usertoDelete;
	}

	@RequestMapping(value = "/EditContact", method = RequestMethod.PUT)
	public Users EditContact(@RequestParam(value = "Id") Integer Id,
			@RequestParam(value = "name", required = false) String Name,
			@RequestParam(value = "lastname", required = false) String LastName,
			@RequestParam(value = "contactnumber", required = false) String ContactNumber) {
		Users usertoEdit = new Users();
		usertoEdit = repository.findById(Id).orElseThrow(()-> new UserNotFoundException("User not found with this Id: "+Id));
	
			
			if (Name != null && usertoEdit.getName() != Name) {

				usertoEdit.setName(Name);
			}
			if (LastName != null && usertoEdit.getLastName() != LastName) {

				usertoEdit.setLastName(LastName);
			}
			if (ContactNumber != null && usertoEdit.getContactNumber() != ContactNumber) {

				usertoEdit.setContactNumber(ContactNumber);
			}
			repository.save(usertoEdit);
		

		return usertoEdit;

	}
}