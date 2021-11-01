package phonebook.users;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="PhoneBookTable")
@SequenceGenerator(name = "id_gen", sequenceName = "id_gen",  initialValue = 1)
public class Users implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "id_gen")

private Integer Id;
	
	@Column(name="name")
	@NotBlank(message="Name cannot be blank or null")
private String name;
	@Column(name="lastname")
	@NotBlank(message="LastName cannot be blank or null")
private String lastname;
	
	@Pattern(regexp = "^99[0-9]{6}",message="Please enter valid phone number of 8 digits starts with 99")
	@Column(name="contactnumber")
private String contactnumber;

	
	
	public Users() {
	}

	public Users(String name, String lastname) {
		super();
		this.name = name;
		this.lastname = lastname;
	}

	public Users( String name,  String lastname, String contactnumber) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.contactnumber = contactnumber;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

}

