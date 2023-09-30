package com.seboonline.services;


import com.seboonline.dtos.SignUpResponseDto;
import com.seboonline.dtos.UserDto;
import com.seboonline.dtos.UserUpdateDto;
import com.seboonline.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService {
    UserDetailsService userDetailsService();
    SignUpResponseDto save(User user);
    User findByUserName(String userName);
    UserDto getUserByUserName(String userName);
    UserDto updateUser(User user, UserUpdateDto userUpdateDto);
}
