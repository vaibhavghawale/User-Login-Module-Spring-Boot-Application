package com.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.model.JwtResponse;
import com.jwt.model.UserLoginDTO;
import com.jwt.services.CustomUserDetailService;
import com.jwt.util.JWTUtil;

@RestController		//for creating API use annotation
public class MyController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private CustomUserDetailService cUserDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> generateToken(@RequestBody UserLoginDTO userDTO) throws Exception{
		
		System.out.println(userDTO);
		
		try {
			//for authentication 
			authManager
			.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("invaild username/password");
		}
		
		UserDetails userDetails =  cUserDetailsService.loadUserByUsername(userDTO.getUserName());
		
		//generate token
		String token  = jwtUtil.generateToken(userDetails);
		System.out.println("JWT"+token);
		
		//{"token":"value"} return json format
		JwtResponse t = new JwtResponse(token);
		return new ResponseEntity<JwtResponse>(t,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
}
