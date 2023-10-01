package com.seboonline.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandlerApp {

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<Violation> handlerUserNameAlreadyExists(UserNameAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new Violation(ex.getUserName(), ex.getMessage()));
    }

    @ExceptionHandler({UserNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<Violation> handlerUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Violation(ex.getUserName(), ex.getMessage()));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Map<String, String>> handlerDisabledUser(DisabledException ex){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(responseBody);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handlerBadCredentials(BadCredentialsException ex){
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(responseBody);
    }



}
