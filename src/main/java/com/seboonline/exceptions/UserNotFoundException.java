package com.seboonline.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private final String userName;
    private final String message = "User not found";

    public UserNotFoundException(String userName) {
        super();
        this.userName = userName;
    }
}
