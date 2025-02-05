package com.nagarro.NoteAPPBackend.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtils {

	// Secret key used for JWT token generation and validation
	  private String SECRET_KEY = "nagarroNoteApp";

	  // Extract the email (subject) from a JWT token
	    public String extractEmail(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    // Extract the expiration date from a JWT token
	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	    
	    // Extract a claim from a JWT token using the provided claims resolver function
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
	    
	    // Extract all claims from a JWT token
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	    }

	 // Check if a JWT token is expired
	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    // Generate a JWT token for a user based on UserDetails
	    public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, userDetails.getUsername());
	    }

	    // Create a JWT token with the provided claims and subject
	    private String createToken(Map<String, Object> claims, String subject) {

	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	    }

	    // Validate a JWT token for a user based on UserDetails
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = extractEmail(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
}