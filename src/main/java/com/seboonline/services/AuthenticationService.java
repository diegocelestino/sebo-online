package com.seboonline.services;


import com.seboonline.dtos.JwtAuthenticationResponse;
import com.seboonline.dtos.LoginDto;

import com.seboonline.dtos.SignUpDto;
import com.seboonline.dtos.SignUpResponseDto;
import com.seboonline.models.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    SignUpResponseDto signUp(SignUpDto request);

    JwtAuthenticationResponse login(LoginDto request);

    void logoff(User user);
}