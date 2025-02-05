package com.nagarro.NoteAPPBackend.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The `User` class represents a user entity in the application.
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {

			    @Id
			    @GeneratedValue(strategy = GenerationType.AUTO)
			    private Long id;
			    private String email;
			    private String firstName;
			    private String lastName;
			    private String password;
			    private Boolean enabled = true;
			
			    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
			    private List<Notes> notes;
			
			    /**
			     * Default constructor for the `User` class.
			     */
			    public User() {
			        // Default constructor
			    }
			
			    // Getter and Setter methods for the class properties
			    // ...
			
			    public String getEmail() {
			        return email;
			    }
			
			    public void setEmail(String email) {
			        this.email = email;
			    }
			
			    public String getFirstName() {
			        return firstName;
			    }
			
			    public void setFirstName(String firstName) {
			        this.firstName = firstName;
			    }
			
			    public String getLastName() {
			        return lastName;
			    }
			
			    public void setLastName(String lastName) {
			        this.lastName = lastName;
			    }
			
			    public String getPassword() {
			        return password;
			    }
			
			    public void setPassword(String password) {
			        this.password = password;
			    }
			
			    public Long getId() {
			        return id;
			    }
			
			    public Boolean getEnabled() {
			        return enabled;
			    }
			
			    public void setEnabled(Boolean enabled) {
			        this.enabled = enabled;
			    }
			
			    @Override
			    public Collection<? extends GrantedAuthority> getAuthorities() {
			        return null;
			    }
			
			    @Override
			    public String getUsername() {
			        return getEmail();
			    }
			
			    @Override
			    public boolean isAccountNonExpired() {
			        return true;
			    }
			
			    @Override
			    public boolean isAccountNonLocked() {
			        return true;
			    }
			
			    @Override
			    public boolean isCredentialsNonExpired() {
			        return true;
			    }
			
			    @Override
			    public boolean isEnabled() {
			        return enabled;
			    }
}
