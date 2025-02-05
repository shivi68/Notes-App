package com.nagarro.NoteAPPBackend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.NoteAPPBackend.models.User;
import com.nagarro.NoteAPPBackend.repository.UserRepository;
import com.nagarro.NoteAPPBackend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
     * Creates a new user with the provided details and user roles.
     * 
     * @param user The user object to be created.
     * @return The created user object.
     * @throws Exception If a user with the same email already exists.
     */
 
	// Creates a new user with the provided details and user roles.
	public User createUser(User user) throws Exception {
		User local = this.userRepository.getUserByEmail(user.getEmail());
		if (local != null) {
			System.out.println("User exists");
			throw new Exception("User already present!");
		} else {
			local = this.userRepository.save(user);
			return local;
		}
	}

	 /**
     * Saves a user in the database.
     * 
     * @param user The user object to be saved.
     * @return The saved user object.
     */
 	// Saves a user in the database.
	public User save(User user) {
		return this.userRepository.save(user);
	}

	
	  /**
     * Retrieves a list of all users.
     * 
     * @return A list of all user objects in the database.
     */
	// Retrieves a list of all users.
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	 /**
     * Retrieves a user by their email.
     * 
     * @param email The email of the user to retrieve.
     * @return The user object corresponding to the given email.
     */
	// Retrieves a user by their email.
	public User showUser(String email) {
		return this.userRepository.getUserByEmail(email);
	}



}

 