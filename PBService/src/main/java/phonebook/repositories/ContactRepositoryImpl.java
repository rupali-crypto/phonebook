package phonebook.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import phonebook.users.Users;

@Repository
@Transactional
public class ContactRepositoryImpl implements ContactRepositoryCustom{

	@PersistenceContext
	EntityManager entity;
	@Override
	public List<Users> getUserswithMob(String contactnumber){
		Query query = entity.createNativeQuery("SELECT * FROM phone_book_table WHERE contactnumber = '" + contactnumber + "'", Users.class);
		return query.getResultList();
		
	}
}
