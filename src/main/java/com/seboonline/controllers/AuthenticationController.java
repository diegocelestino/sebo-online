package com.seboonline.controllers;

import com.seboonline.dtos.*;
import com.seboonline.models.User;
import com.seboonline.services.AuthenticationService;
import com.seboonline.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authenticationService.signUp(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @PostMapping("/logoff")
    public ResponseEntity<Void> logoff(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        this.authenticationService.logoff(user);
        return ResponseEntity.noContent().build();
    }

}
