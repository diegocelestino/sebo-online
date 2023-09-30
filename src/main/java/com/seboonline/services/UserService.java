package com.seboonline.services;


import com.seboonline.dtos.UserDto;
import com.seboonline.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    UserDto save(User user);
    Optional<User> findByName(String userName);
}
