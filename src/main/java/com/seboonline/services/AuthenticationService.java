package com.seboonline.services;


import com.seboonline.dtos.JwtAuthenticationResponse;
import com.seboonline.dtos.SignInDto;

import com.seboonline.dtos.SignUpDto;
import com.seboonline.dtos.UserDto;

public interface AuthenticationService {
    UserDto signUp(SignUpDto request);

    JwtAuthenticationResponse signIn(SignInDto request);
}