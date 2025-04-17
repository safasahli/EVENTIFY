package com.example.demo.security.Jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest; // Corrected to Jakarta EE version
import jakarta.servlet.http.HttpServletResponse; // Corrected to Jakarta EE version
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Log unauthorized access attempt
        logger.error("Unauthorized access attempt: {}", authException.getMessage());

        // Send Unauthorized error response (HTTP status 401)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
