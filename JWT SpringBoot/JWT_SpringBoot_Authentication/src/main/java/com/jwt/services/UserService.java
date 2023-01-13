package com.jwt.services;


import com.jwt.exceptions.UserExecption;
import com.jwt.model.User;




public interface UserService {
	
	public User registerUser(User user) throws UserExecption;
	public User findUserById(Integer id) throws UserExecption;
}
