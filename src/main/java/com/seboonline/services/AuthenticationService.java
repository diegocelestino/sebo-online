package com.seboonline.services;


import com.seboonline.dtos.JwtAuthenticationResponse;
import com.seboonline.dtos.SignInDto;

import com.seboonline.dtos.SignUpDto;
import com.seboonline.dtos.SignUpResponseDto;

public interface AuthenticationService {
    SignUpResponseDto signUp(SignUpDto request);

    JwtAuthenticationResponse signIn(SignInDto request);
}