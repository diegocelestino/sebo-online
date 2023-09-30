package com.seboonline.services.impl;


import com.seboonline.dtos.JwtAuthenticationResponse;
import com.seboonline.dtos.SignInDto;
import com.seboonline.dtos.SignUpDto;
import com.seboonline.dtos.SignUpResponseDto;
import com.seboonline.enums.Role;
import com.seboonline.models.User;
import com.seboonline.services.AuthenticationService;
import com.seboonline.services.JwtService;
import com.seboonline.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public SignUpResponseDto signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(signUpDto.getName())
                .userName(signUpDto.getUserName())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .active(true)
                .roles(List.of(Role.BUYER, Role.SELLER))
                .startDate(Timestamp.from(Instant.now()))
                .build();
        return userService.save(user);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInDto signInDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDto.getUserName(), signInDto.getPassword()));
        var user = userService.findByUserName(signInDto.getUserName());
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
