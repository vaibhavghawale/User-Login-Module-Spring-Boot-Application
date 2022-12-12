package com.masai.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.models.CurrentAdminSession;


public interface UserSessionRepo extends JpaRepository<CurrentAdminSession, Integer>{
	
	public CurrentAdminSession findByAdId(String adId);
}
