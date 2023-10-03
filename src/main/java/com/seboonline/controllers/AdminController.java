package com.seboonline.controllers;

import com.seboonline.dtos.*;
import com.seboonline.models.User;
import com.seboonline.services.AdminService;
import com.seboonline.services.AuthenticationService;
import com.seboonline.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/admin")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final AdminService adminService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> usersDto = adminService.getAllUsers();
        return ResponseEntity.ok(usersDto);
    }

}
