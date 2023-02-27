package com.multiverse.api.springsecuritybackendmultiverseapi.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        // Set the response status and headers
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Custom-Header", "Custom Value");

        // Create a ResponseEntity object with the modified response
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN)
                .header("Custom-Header", "Custom Value")
                .body("Access Denied, please create an account or login");

        // Write the response to the output stream
        response.getOutputStream().write(responseEntity.getBody().getBytes());
    }
}
