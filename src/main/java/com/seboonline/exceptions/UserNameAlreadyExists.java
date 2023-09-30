package com.seboonline.exceptions;

import lombok.Getter;

@Getter
public class UserNameAlreadyExists extends RuntimeException {
    private final String userName;
    private final String message = "User name already exists";

    public UserNameAlreadyExists(String userName) {
        super();
        this.userName = userName;
    }
}
