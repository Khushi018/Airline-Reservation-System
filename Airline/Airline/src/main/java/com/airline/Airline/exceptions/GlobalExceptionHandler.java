package com.airline.Airline.exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex,
            WebRequest request) {
        logger.error("Unhandled exception occurred: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.put("message", ex.getMessage());
        errorResponse.put("details", request.getDescription(false));
        return new ResponseEntity<Map<String, Object>>(errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle Airport Not Found Exception
    @ExceptionHandler(AirportNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAirportNotFoundException(AirportNotFoundException ex) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

   
    // Handle Missing Request Body Exception
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleMissingRequestBodyException(
            org.springframework.http.converter.HttpMessageNotReadableException ex) {
        return buildErrorResponse("Required request body is missing or invalid", HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found exceptions (like Airport or Flight not found)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex,
            WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseException(DataIntegrityViolationException ex,
            WebRequest request) {
        return buildErrorResponse("Data integrity violation: " + ex.getMostSpecificCause().getMessage(),
                HttpStatus.CONFLICT);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return new ResponseEntity<>(response, status);

    }
}
