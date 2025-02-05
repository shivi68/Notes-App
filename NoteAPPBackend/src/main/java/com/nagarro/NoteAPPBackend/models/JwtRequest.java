package com.nagarro.NoteAPPBackend.models;

public class JwtRequest {
	
	String userName;
	String password;
	
		/**
	     * Default constructor for JwtRequest.
	     */
		public JwtRequest() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		 /**
	     * Parameterized constructor for JwtRequest.
	     *
	     * @param userName The username for authentication.
	     * @param password The password for authentication.
	     */
		public JwtRequest(String userName, String password) {
			super();
			this.userName = userName;
			this.password = password;
		}
		
		
		 /**
	     * Get the username from the JwtRequest.
	     *
	     * @return The username for authentication.
	     */
		public String getUserName() {
			return userName;
		}
		
		 /**
	     * Set the username in the JwtRequest.
	     *
	     * @param userName The username for authentication.
	     */
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		  /**
	     * Get the password from the JwtRequest.
	     *
	     * @return The password for authentication.
	     */
		
		public String getPassword() {
			return password;
		}
		
		
		/**
	     * Set the password in the JwtRequest.
	     *
	     * @param password The password for authentication.
	     */
		public void setPassword(String password) {
			this.password = password;
		}
}