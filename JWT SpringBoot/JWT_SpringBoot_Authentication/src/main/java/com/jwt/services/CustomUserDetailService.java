package com.jwt.services;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.repository.UserRepo;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo uRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 Optional<com.jwt.model.User> findUser = uRepo.findByUserName(username);
		 
		 com.jwt.model.User find = findUser.get();
		
		if(username.equals(find.getUserName())) {
			
			return new User(find.getUserName(), find.getPassword(), new ArrayList<>());
		}
		else
			throw new UsernameNotFoundException("User not found");
	}
}
