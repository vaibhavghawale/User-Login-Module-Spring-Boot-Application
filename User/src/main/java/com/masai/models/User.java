package com.masai.models;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	@Size(min = 3,max = 10,message = "Name length shoud be minimum 3 and maximum 10")
	private String firstName;
	
	@Size(min = 3,max = 10,message = "Name length shoud be minimum 3 and maximum 10")
	private String lastName;
	
	@Size(min = 10,max = 10,message = "MobileNumber length shoud be 10")
	private String mobileNumber;
	
	@Email
	@Size(min=4,message = "Password length shoud be minimum 4")
	private String email;

	
	
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include alphanumerics and special characters")
	private String password;
	
	@Past
	private Date DateOfBirth;
	

}
