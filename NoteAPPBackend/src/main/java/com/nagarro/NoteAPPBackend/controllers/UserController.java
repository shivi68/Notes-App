package com.nagarro.NoteAPPBackend.controllers;


import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.nagarro.NoteAPPBackend.impl.UserDetailsServiceImpl;
//import com.nagarro.NoteAPPBackend.impl.NoteServiceImpl;
import com.nagarro.NoteAPPBackend.impl.UserServiceImpl;
import com.nagarro.NoteAPPBackend.models.User;

//The @RestController annotation indicates that this class handles HTTP requests and returns responses as JSON.
@RestController
// The @CrossOrigin annotation allows cross-origin requests from any origin (denoted by "*").
@CrossOrigin("*")
// This class serves as the REST controller for user-related operations.
public class UserController {
	

	// Autowired instance of UserDetailsServiceImpl for user details retrieval.
	@Autowired
	private UserDetailsServiceImpl userDeatilsServiceImpl;

	// Autowired instance of UserServiceImpl for user-related operations.
	@Autowired
	private UserServiceImpl userService;
	
	// Autowired instance of BCryptPasswordEncoder for password hashing.
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	
	//For getting Current User
		@GetMapping("/current-user")
		@CrossOrigin("*")
		public User getCurrentUser(Principal principal) {
			System.out.println(principal.getName());
			return ((User) this.userDeatilsServiceImpl.loadUserByUsername(principal.getName()));
		}
	
	//	Registering a new User for Note Application
	@PostMapping("/user/signup")
	@CrossOrigin("*")
	public User registerUser(@RequestBody User user) throws Exception {
		try {
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			return this.userService.createUser(user);
		}catch (Exception e) {
			throw new Exception("User with email " + user.getEmail() + "already exists!!");
		}
	}
	
	

}	

