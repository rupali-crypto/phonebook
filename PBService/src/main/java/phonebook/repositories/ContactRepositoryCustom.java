package phonebook.repositories;

import java.util.List;

import phonebook.users.Users;

public interface ContactRepositoryCustom {

	List<Users> getUserswithMob(String contactnumber);
}
