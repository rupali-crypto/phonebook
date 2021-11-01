package phonebook.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import phonebook.users.Users;

@Repository
public interface ContactRepository extends CrudRepository<Users, Integer>,ContactRepositoryCustom{



}
