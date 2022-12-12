package com.masai.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

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
import com.masai.exception.UserExecption;
import com.masai.models.User;
import com.masai.models.UserLoginDTO;
import com.masai.service.UserService;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired(required = true)
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerAdmin(@RequestBody User user) throws UserExecption {
		
		User saveAdmin = userService.registerUser(user);
		
		return new ResponseEntity<User>(saveAdmin,HttpStatus.CREATED);
		
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findAdminById(@PathVariable("id") Integer id) throws UserExecption{
		User user = userService.findUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	@PutMapping("/user/")
	public ResponseEntity<User> updateAdminDetails(@RequestBody User user,@RequestParam(required = false) String key) throws UserExecption{
		
		User updatedAdmin = userService.updateUserDetails(user, key);
		
		return new ResponseEntity<User>(updatedAdmin,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginAdminHandler( @RequestBody UserLoginDTO aDto) throws LoginException{
		String string = userService.adminLogInToAccount(aDto);
		
		return new ResponseEntity<String>(string,HttpStatus.OK);
	}
	
	
}
