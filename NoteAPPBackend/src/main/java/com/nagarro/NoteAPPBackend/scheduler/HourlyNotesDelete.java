package com.nagarro.NoteAPPBackend.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.springframework.data.domain.PageRequest;
import com.nagarro.NoteAPPBackend.models.User;
import com.nagarro.NoteAPPBackend.repository.NoteRepository;
import com.nagarro.NoteAPPBackend.repository.UserRepository;



/**
 * The `HourlyNotesDelete` class is responsible for scheduling a task to delete
 * older notes for all users hourly.
 */
@Component
public class HourlyNotesDelete {
	
		@Autowired
		private NoteRepository noteRepository;

	    @Autowired
	    private UserRepository userRepository;

	    
	    /**
	     * Scheduled method to delete older notes for all users every hour.
	     */

	    @Scheduled(cron = "0 * * * * *")  //on the basis of every one hour
	       
	  // @Scheduled(cron = "*/10 * * * * *") // on the basis of every 10 sec

	 
	    	public void deleteNotesHourly() {
	    	    List<User> users = userRepository.findAll();
	    	    for (User user : users) {
	    	        List<Long> lastTenNoteIds = noteRepository.findLastTenNoteIds(user.getId(), PageRequest.of(0, 10));
	    	        noteRepository.deleteUsers(user.getId(), lastTenNoteIds);
	    	    }
	    	}
	    
	}

	 

	 

	