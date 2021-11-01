package phonebook.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import phonebook.Exceptions.ContactNotFoundException;
import phonebook.Exceptions.UserNotFoundException;
import phonebook.repositories.ContactRepository;
import phonebook.users.Users;

@RestController
@RequestMapping("/phonebook/api")
@Validated
public class PBControllers implements Serializable {

	Logger logger = LoggerFactory.getLogger(PBControllers.class);

	@Autowired
	ContactRepository repository;

	@RequestMapping(value = "/GetAllContacts", method = RequestMethod.GET)

	public Iterable<Users> getAllEntries() {
		logger.info("****Started GetAllContacts()****");
		Iterable<Users> phonebookCollection = repository.findAll();
		logger.warn("****finding all contacts for you****");
		return phonebookCollection;

	}

	@RequestMapping(value = "/GetContact-byNo", method = RequestMethod.GET, params = { "contactnumber" })
	public List<Users> getFiltered(String contactnumber) {
		List<Users> user = new ArrayList<Users>();
		user = repository.getUserswithMob(contactnumber);
		if (!user.isEmpty()) {
			return user;
		} else {
			throw new UserNotFoundException("User not found with this ContactNumber: " + contactnumber);
		}
	}

	@RequestMapping(value = "/GetContact-byId", method = RequestMethod.GET, params = {
			"Id" }, name = "Get a contact by id")
	public Users getUserbyId(@RequestParam(value = "Id") Integer Id) {
		Users user = new Users();
		logger.info("****Inside getUser method****");
		logger.warn("****Searching " + Id + " for you****");
		user = repository.findById(Id)

				.orElseThrow(() -> new ContactNotFoundException("User not found with this contact number: " + Id));
		logger.info("****Search for " + Id + " is completed***");
		return user;

	}

	@PostMapping("/create-contact")
	public Users createEmployee(@Valid @RequestBody Users newUser) {

		Users savedUser = repository.save(newUser);

		return savedUser;
	}

	@RequestMapping(value = "/Delete-Contact", method = RequestMethod.DELETE)
	public Users DeleteContact(@RequestParam(value = "Id") Integer Id) {
		logger.info("***Inside DeleteContact method***");
		Users usertoDelete = new Users();
		usertoDelete = repository.findById(Id)
				.orElseThrow(() -> new UserNotFoundException("User not found with this Id: " + Id));
		logger.warn("***seraching for the user to delete***");
		repository.deleteById(usertoDelete.getId());
		logger.info("**" + usertoDelete + " is successfully deleted****");
		return usertoDelete;
	}

	@RequestMapping(value = "/Edit-Contact", method = RequestMethod.PUT)
	public Users EditContact(@RequestParam(value = "Id") Integer Id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "contactnumber", required = false) String contactnumber) {
		Users usertoEdit = new Users();
		logger.info("***Inside EditContact method***");
		usertoEdit = repository.findById(Id)
				.orElseThrow(() -> new UserNotFoundException("User not found with this Id: " + Id));
		logger.warn("***processing your information to update the existing contact***");

		if (name != null && usertoEdit.getName() != name) {

			usertoEdit.setName(name);
		}

		if (lastname != null && usertoEdit.getLastname() != lastname) {

			usertoEdit.setLastname(lastname);
		}
		if (contactnumber != null && usertoEdit.getContactnumber() != contactnumber) {

			usertoEdit.setContactnumber(contactnumber);
		}
		repository.save(usertoEdit);
		logger.info("**" + usertoEdit + " is successfully updated****");

		return usertoEdit;

	}
}