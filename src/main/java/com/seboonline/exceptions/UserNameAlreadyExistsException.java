package com.seboonline.exceptions;

import lombok.Getter;

@Getter
public class UserNameAlreadyExistsException extends RuntimeException {
    private final String userName;
    private final String message = "User name already exists";

    public UserNameAlreadyExistsException(String userName) {
        super();
        this.userName = userName;
    }
}
