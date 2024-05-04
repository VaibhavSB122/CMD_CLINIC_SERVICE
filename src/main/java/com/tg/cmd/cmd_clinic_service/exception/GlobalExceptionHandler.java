package com.tg.cmd.cmd_clinic_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(JwtTokenMissingException.class)
    public ResponseEntity<Object> handleJwtTokenMissingException(JwtTokenMissingException ex) {
        // Log the exception
        logger.error("JWT Token is missing: {}", ex.getMessage());
        
        // Return a meaningful error response to the client
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("error", "Unauthorized");
        responseBody.put("message", "JWT Token is missing in the request headers");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    // Add more exception handlers for other custom exceptions or standard Spring exceptions as needed
}
