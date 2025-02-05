package com.nagarro.NoteAPPBackend.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.NoteAPPBackend.models.Notes;
import com.nagarro.NoteAPPBackend.models.User;
import com.nagarro.NoteAPPBackend.repository.NoteRepository;
import com.nagarro.NoteAPPBackend.repository.UserRepository;
import com.nagarro.NoteAPPBackend.services.NoteService;
import com.nagarro.NoteAPPBackend.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user/notes") // Updated base path to /user/notes
public class NoteController {


	// Autowired UserRepository for accessing user-related data and operations.
    @Autowired
    private UserRepository userRepository;
	
	//Autowired NoteRepository for accessing note-related data and operations.
    @Autowired
    private NoteRepository noteRepository;        
    
    //Autowired UserService for handling user-related business logic.
    @Autowired
    private UserService userService;
    
    // Autowired NoteService for handling note-related business logic.
    @Autowired
    private NoteService noteService;
    
    
    @CrossOrigin("*")
    @PostMapping("/add-new-notes") // Updated endpoint to /user/notes/create
    public Notes addNewNote(Principal principal, @RequestBody Notes note) {
        User user = getUserByEmail(principal.getName());
        note.setUser(user);
        return noteRepository.save(note);
    }
    
    

    @CrossOrigin("*")
    @GetMapping("/viewAllNotes")
    // Get the authenticated user based on the Principal
    public ResponseEntity<List<Notes>> getAllLatestNotes(Principal principal) {
        User user = getUserByEmail(principal.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Notes> latestNotes = noteService.getLatestNotesForUser(user);
        return ResponseEntity.ok(latestNotes);
    }


    @CrossOrigin("*")
    @GetMapping("/allNotes")// Endpoint to retrieve all notes for the authenticated user
    public List<Notes> getAllNotes(Principal principal) {
    	// Get the authenticated user based on the Principal
        User user = getUserByEmail(principal.getName());
        return noteRepository.findByUser(user);
    }
    
 
 
    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}") // Updated endpoint to /user/notes/{id}
    public ResponseEntity<?> deleteNote(Principal principal, @PathVariable Integer id) {
        User user = getUserByEmail(principal.getName());
        Optional<Notes> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent() && optionalNote.get().getUser().equals(user)) {
            noteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }
    
    // Custom method to get a user by email
    private User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    } 
    
 
 }