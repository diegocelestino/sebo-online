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
@RequestMapping("api/v1/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserDto> getUser(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        UserDto userDto = this.userService.getUserByUserName(user.getUsername());
        return ResponseEntity.ok(userDto);
    }

    @PutMapping()
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userUpdateDto, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        UserDto userDto = this.userService.updateUser(user, userUpdateDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping()
    public ResponseEntity<UserDto> delete(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        this.userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }

}
