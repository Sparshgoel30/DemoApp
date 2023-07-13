package com.api.exittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.api.exittest.model.Dashboard;
import com.api.exittest.model.Users;
import com.api.exittest.repo.UsersRepo;

@RestController
@RequestMapping(path = "/api/")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UsersRepo userRepository;

	/**
	 * get all users
	 * 
	 * @return
	 */
	@GetMapping("/getusers")
	public List<Users> getAllUsers() {
		System.out.println("Get Users Clicked");
		return userRepository.findAll();
	}

	/**
	 * register a new user
	 * 
	 * @param newUser
	 * @return
	 */
	@PostMapping("/register")
	public Users registerUser(@RequestBody Users newUser) {
		System.out.println("New User Registered");
		return userRepository.save(newUser);
	}

	/**
	 * user-login authentication
	 * 
	 * @param userData
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Users userData) {

		System.out.println("Login Clicked");

		// Print user data received from the request
		System.out.println(userData);

		// Find the user by email in the userRepository
		Users user = userRepository.findByEmail(userData.getEmail());

		// Check if a user with the provided email exists and the password matches
		if (user.getPassword().equals(userData.getPassword()))
			// Return a response entity with the user object and HTTP status 200 (OK)
			return ResponseEntity.ok(user);
		// Return a response entity with HTTP status 500 (Internal Server Error)
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

	/**
	 * get count of users
	 * 
	 * @return count
	 */
	@GetMapping("/getTotalUsersCount")
	public int getTotalUsersCount() {
		// Print message indicating the retrieval of the total users count
		System.out.println("Get Total Users Count");
		// Retrieve all users and return the count
		return userRepository.findAll().size();
	}

}
