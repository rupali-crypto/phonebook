package phonebook.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.info.Contact;
import phonebook.repositories.ContactRepository;
import phonebook.users.Users;

@RequestMapping("/api")
@RestController
public class PBControllers {

	@Autowired
	ContactRepository repository;

	@RequestMapping(value = "/hello",method=RequestMethod.GET)
	public String getHelloWorld() {
		String message = "Hello";
		return message;
	}

	@RequestMapping(value = "/GetAllcontacts", method = RequestMethod.GET)
	
	public Iterable<Users> getAllEntries() {

		Iterable<Users> phonebookCollection = repository.findAll();
		return phonebookCollection;

	}

	@RequestMapping(value = "/GetContacts", method = RequestMethod.GET, params = { "contactnumber" })
	public Users getUser(@RequestParam(value = "contactnumber") String ContactNumber) {
		Users user = new Users();
		Optional<Users> optionalUser = repository.findById(Integer.parseInt(ContactNumber));
		if (optionalUser.isPresent()) {
			user = optionalUser.get();

		}
		return user;
	}

	@RequestMapping(value = "/AddContacts", method = RequestMethod.POST)
	public Users AddContact(@RequestParam(value = "name") String Name,
			@RequestParam(value = "lastname") String LastName,
			@RequestParam(value = "contactnumber") String ContactNumber) {
		Users newuser = new Users(Name, LastName, ContactNumber);
		repository.save(newuser);
		return newuser;
	}

	@RequestMapping(value = "/DeleteContacts", method = RequestMethod.DELETE)
	public Users DeleteContact(@RequestParam(value = "Id") Integer Id) {
		Users usertoDelete = new Users();
		Optional<Users> optionalUser = repository.findById(Id);
		if (optionalUser.isPresent()) {
			usertoDelete = optionalUser.get();
			repository.deleteById(usertoDelete.getId());

		}
		return usertoDelete;
	}

	@RequestMapping(value = "/EditContacts", method = RequestMethod.PUT)
	public Users EditContact(@RequestParam(value = "Id") Integer Id,
			@RequestParam(value = "name", required = false) String Name,
			@RequestParam(value = "lastname", required = false) String LastName,
			@RequestParam(value = "contactnumber", required = false) String ContactNumber) {
		Users usertoEdit = new Users();
		Optional<Users> optionalUser = repository.findById(Id);
		if (optionalUser.isPresent()) {
			usertoEdit = optionalUser.get();
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
		}

		return usertoEdit;

	}
}