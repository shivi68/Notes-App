package com.nagarro.NoteAPPBackend.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * The `Notes` class represents a note entity in the application.
 */
@Entity
@Table(name = "notes")
public class Notes {

		    @Id
		    @GeneratedValue(strategy = GenerationType.AUTO)
		    private int id;
		
		    private String title;
		
		    @Column(nullable = false, length = 500)
		    private String description;
		
		    @Column(nullable = false)
		    private LocalDateTime createdDt = LocalDateTime.now();
		
		    @ManyToOne
		    @JoinColumn(name = "user_id", nullable = false)
		    @OnDelete(action = OnDeleteAction.CASCADE)
		    private User user;
		
		    /**
		     * Default constructor for the `Notes` class.
		     */
		    public Notes() {
		        // Default constructor
		    }
		
		   
		    // Getter and Setter methods for the class properties
		    
		    public int getId() {
		        return id;
		    }
		
		    public void setId(int id) {
		        this.id = id;
		    }
		
		    public String getTitle() {
		        return title;
		    }
		
		    public void setTitle(String title) {
		        this.title = title;
		    }
		
		    public String getDescription() {
		        return description;
		    }
		
		    public void setDescription(String description) {
		        this.description = description;
		    }
		
		    public User getUser() {
		        return user;
		    }
		    
		    public void setUser(User user) {
		    	this.user = user;
		    }
		
		    public LocalDateTime getCreatedDt() {
		        return createdDt;
		    }
		
		    public void setCreatedDt(LocalDateTime createdDt) {
		        this.createdDt = createdDt;
		    }
}
