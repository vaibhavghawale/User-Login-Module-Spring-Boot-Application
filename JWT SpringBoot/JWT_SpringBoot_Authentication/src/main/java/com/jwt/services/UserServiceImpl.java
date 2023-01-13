package com.jwt.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementationSource;

import com.jwt.exceptions.UserExecption;
import com.jwt.model.User;
import com.jwt.repository.UserRepo;

import net.bytebuddy.utility.RandomString;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo aRepo;
	

	@Override
	public User registerUser(User user) throws UserExecption {
		
		Optional<User> existadmin = aRepo.findByUserName(user.getUserName());
		
		if(existadmin!=null)
			throw new UserExecption("Admin already registered with UserName "+user.getUserName());
		
		return aRepo.save(user);
	}


	@Override
	public User findUserById(Integer id) throws UserExecption {
		
		return aRepo.findById(id).orElseThrow(()->new UserExecption("Account does not found with id: "+id));
		
	}



	

}
