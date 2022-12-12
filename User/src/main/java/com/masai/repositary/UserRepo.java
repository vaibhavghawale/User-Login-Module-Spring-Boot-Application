package com.masai.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.models.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);

}
