package com.seboonline.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerApp {

    @ExceptionHandler(UserNameAlreadyExists.class)
    public ResponseEntity<Violation> handlerUserNameAlreadyExists(UserNameAlreadyExists ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new Violation(ex.getUserName(), ex.getMessage()));
    }
}
