package com.jwt.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jwt.exceptions.UserExecption;
import com.jwt.model.User;
import com.jwt.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo aRepo;
	

	@Override
	public User registerUser(User user) throws UserExecption {
	
		return aRepo.save(user);
	}


	@Override
	public User findUserById(Integer id) throws UserExecption {
		
		return aRepo.findById(id).orElseThrow(()->new UserExecption("Account does not found with id: "+id));
		
	}



	

}
