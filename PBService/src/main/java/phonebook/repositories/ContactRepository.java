package phonebook.repositories;

import org.springframework.data.repository.CrudRepository;

import phonebook.users.Users;

public interface ContactRepository extends CrudRepository<Users, Integer>{

}
