package com.nagarro.NoteAPPBackend.configuration;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	/**
     * Commence method handles unauthorized requests by sending an error response.
     * 
     * @param request       The HTTP request
     * @param response      The HTTP response
     * @param authException The authentication exception
     * @throws IOException      In case of an I/O error while processing the request
     * @throws ServletException In case of a servlet error while processing the
     *                          request
     */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		 // Send an HTTP 401 Unauthorized response
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized : Server");

	}
}
