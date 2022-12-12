package com.masai.models;

import lombok.Data;

@Data
public class UserLoginDTO {
	
	private String email;
	private String password;
	
	public UserLoginDTO() {
		
	}

	public UserLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	

}
