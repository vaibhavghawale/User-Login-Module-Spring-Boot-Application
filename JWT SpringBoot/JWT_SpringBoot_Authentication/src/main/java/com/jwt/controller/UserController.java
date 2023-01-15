package com.jwt.controller;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.exceptions.UserExecption;
import com.jwt.model.User;
import com.jwt.services.UserService;

import net.bytebuddy.utility.RandomString;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerAdmin(@RequestBody User user) throws UserExecption {
		
		User saveAdmin = userService.registerUser(user);
		
		return new ResponseEntity<User>(saveAdmin,HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<String> welcome(){
		String str = "welcome baby";
		return new ResponseEntity<String>(str,HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findAdminById(@PathVariable("id") Integer id) throws UserExecption{
		User user = userService.findUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
}
