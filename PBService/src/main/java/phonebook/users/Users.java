package phonebook.users;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="PBEntry")
public class Users implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

private Integer Id;
	@Column(name="name")
private String Name;
	@Column(name="lastname")
private String LastName;
	@Column(name="contactnumber")
private String ContactNumber;



public Users() {
}
public Users(String name, String lastName, String contactNumber) {
	
	this.Name = name;
	this.LastName = lastName;
	this.ContactNumber = contactNumber;
}

public Integer getId() {
	return Id;
}
public void setId(Integer id) {
	Id = id;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getLastName() {
	return LastName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public String getContactNumber() {
	return ContactNumber;
}

public void setContactNumber(String contactNumber) {
	ContactNumber = contactNumber;
}


}
