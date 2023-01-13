package com.jwt.model;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}
	
	

}
