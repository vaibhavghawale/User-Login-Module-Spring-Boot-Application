package com.masai.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMImplementationSource;
import com.masai.exception.UserExecption;
import com.masai.models.User;
import com.masai.models.UserLoginDTO;
import com.masai.models.CurrentAdminSession;

import com.masai.repositary.UserRepo;
import com.masai.repositary.UserSessionRepo;

import net.bytebuddy.utility.RandomString;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired(required = true)
	private UserRepo aRepo;
	
	@Autowired(required = true)
	private UserSessionRepo sessionRepo;


	@Override
	public User registerUser(User user) throws UserExecption {
		
		User existadmin = aRepo.findByEmail(user.getEmail());
		
		if(existadmin != null)
			throw new UserExecption("Admin already registered with Email "+user.getEmail());
		
		return aRepo.save(user);
	}


	@Override
	public User findUserById(Integer id) throws UserExecption {
		
		return aRepo.findById(id).orElseThrow(()->new UserExecption("Account does not found with id: "+id));
		
	}

	@Override
	public User updateUserDetails(User user, String key) throws UserExecption {
		
		CurrentAdminSession loggedAdmin = sessionRepo.findByAdId(key);
		
		if(loggedAdmin == null) {
			throw new UserExecption("Please provide valid key to update details");
		}
		
		if(user.getAdminId()==loggedAdmin.getAdminId()) {
			return aRepo.save(user);
		}else
		throw new UserExecption("Invalid admin details, please login first");
	}

	@Override
	public String adminLogInToAccount(UserLoginDTO adminLoginDTO) throws LoginException {
		
		User existingAdmin= aRepo.findByEmail(adminLoginDTO.getEmail());
		
		if(existingAdmin == null) {
			throw new LoginException("Please enter a valid Email");
		}
		
		Optional<CurrentAdminSession> validOptional = sessionRepo.findById(existingAdmin.getAdminId());
		
		if (validOptional.isPresent()) {
			throw new LoginException("Already logged in with this Email "+existingAdmin.getEmail());
		}
		
		if (existingAdmin.getPassword().equals(adminLoginDTO.getPassword())) {
			
			String keyString = RandomString.make(6);
			
			CurrentAdminSession currentSession = new CurrentAdminSession(existingAdmin
													.getAdminId(),keyString,LocalDateTime.now());
			
			sessionRepo.save(currentSession);
			
			return currentSession.toString();
			
		}else 
			throw new LoginException("Please enter a valid password");
		
	}	

}
