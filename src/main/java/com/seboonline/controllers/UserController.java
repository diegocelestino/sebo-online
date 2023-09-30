package com.seboonline.controllers;

import com.seboonline.dtos.JwtAuthenticationResponse;
import com.seboonline.dtos.SignInDto;
import com.seboonline.dtos.SignUpDto;
import com.seboonline.dtos.UserDto;
import com.seboonline.models.User;
import com.seboonline.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authenticationService.signUp(signUpDto));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(authenticationService.signIn(signInDto));
    }


}
