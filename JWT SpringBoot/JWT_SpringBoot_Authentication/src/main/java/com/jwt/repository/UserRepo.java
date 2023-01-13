package com.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.model.User;




@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	public Optional<User> findByUserName(String userName);

}
