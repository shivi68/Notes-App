package com.nagarro.NoteAPPBackend.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.NoteAPPBackend.configuration.JwtUtils;
import com.nagarro.NoteAPPBackend.impl.UserDetailsServiceImpl;
import com.nagarro.NoteAPPBackend.models.JwtRequest;
import com.nagarro.NoteAPPBackend.models.JwtResponse;
import com.nagarro.NoteAPPBackend.models.User;

@RestController
@CrossOrigin("*")
// This class serves as the RESTful controller for handling authentication-related operations.
public class AuthenticationController {
	
	// JwtUtils is a utility class for working with JSON Web Tokens (JWTs) used for securing
	// and authenticating API endpoints.
	@Autowired
	private JwtUtils jwtUtils;

	// The AuthenticationManager is responsible for authenticating user credentials during login.
	@Autowired
	private AuthenticationManager authenticationManager;

	// The UserDetailsServiceImpl is a custom implementation of the UserDetailsService interface
	// and provides user details retrieval for authentication and authorization.
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

	

	/**
	 * Generates a JSON Web Token (JWT) for user authentication.
	 *
	 * @param jwtRequest The request containing user credentials (username and password).
	 * @return A ResponseEntity containing the JWT if authentication is successful.
	 * @throws Exception If the user is not found or if there's an issue generating the token.
	 */
	@PostMapping("/generate-token")
	@CrossOrigin("*")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			// Attempt to authenticate the user with the provided credentials
			authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
	        // If the username is not found, throw an exception with an error message
			throw new Exception("User not found");
		}
		
		  // Load user details (e.g., roles) based on the authenticated username
		UserDetails userDetails = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		
		System.out.println(userDetails);
		
		// Generate a JWT token based on the user details
		String s = this.jwtUtils.generateToken(userDetails);
		
		 // Wrap the JWT token in a JwtResponse object and return it as an HTTP response
		return ResponseEntity.ok(new JwtResponse(s));
	}

	/**
	 * Authenticates a user with the provided username and password.
	 *
	 * @param username The username of the user attempting to authenticate.
	 * @param password The password of the user attempting to authenticate.
	 * @throws Exception if authentication fails, with specific exceptions for different failure scenarios.
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			 // Attempt to authenticate the user using the provided username and password
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("User Disable");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credentials!!");
		}
	}


}
