package com.nagarro.NoteAPPBackend.models;

public class JwtResponse {
	
			String token;
			
			 /**
		     * Default constructor for JwtResponse.
		     */
			public JwtResponse() {
				super();
				// TODO Auto-generated constructor stub
			}
			
		    /**
		     * Parameterized constructor for JwtResponse.
		     *
		     * @param token The JWT token to be included in the response.
		     */
			public JwtResponse(String token) {
				super();
				this.token = token;
			}
		
			 /**
		     * Get the JWT token from the JwtResponse.
		     *
		     * @return The JWT token.
		     */
			public String getToken() {
				return token;
			}
			
			/**
		     * Set the JWT token in the JwtResponse.
		     *
		     * @param token The JWT token to be set.
		     */
		
			public void setToken(String token) {
				this.token = token;
			}

}
