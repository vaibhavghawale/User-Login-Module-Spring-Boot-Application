package com.masai.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.masai.exception.UserExecption;
import com.masai.models.User;
import com.masai.models.UserLoginDTO;


public interface UserService {
	
	public User registerUser(User user) throws UserExecption;
	public User findUserById(Integer id) throws UserExecption;
	public User updateUserDetails(User user,String key) throws UserExecption;
	
	public String adminLogInToAccount(UserLoginDTO adminLoginDTO) throws LoginException;

	
}
