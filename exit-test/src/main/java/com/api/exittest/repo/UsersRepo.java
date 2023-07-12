package com.api.exittest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.exittest.model.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, String>{
	
	// Find a user by email
	 Users findByEmail(String email);
}
