package com.spring.spring_rest.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.spring.spring_rest.entity.StudentErrorResponse;
import com.spring.spring_rest.exception.StudentNotFoundException;

/**
 * Global exception handler for handling exceptions in student REST APIs.
 */
@ControllerAdvice
public class StudentExceptionHandler {

    /**
     * Handles exceptions when a student is not found.
     *
     * @param ex the StudentNotFoundException thrown
     * @return a response containing error details with HTTP 404 status
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(
            StudentNotFoundException ex) {

        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setMessage(ex.getMessage());
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles general exceptions caused by invalid requests.
     *
     * @param ex the exception thrown
     * @return a response containing error details with HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StudentErrorResponse> handleInvalidInteger(
            MethodArgumentTypeMismatchException ex) {

        StudentErrorResponse err = new StudentErrorResponse();

        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage("Invalid data. Only Integers are accepted");
        err.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(
                err,
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Handles validation errors for invalid student input fields.
     *
     * @param ex the MethodArgumentNotValidException thrown
     * @return a map of field names and validation error messages
     *         with HTTP 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> err = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            err.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}