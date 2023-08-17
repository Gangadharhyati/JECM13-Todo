package dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	// @Column(unique = true)
	String email;
	long mobile;
	String gender;
	String adress;
	LocalDate dob;
	String[] lang;
	String password;

	@OneToMany
	List<Task> tasks;

}
