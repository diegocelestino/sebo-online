package com.seboonline.services.impl;

import com.seboonline.dtos.UserDto;
import com.seboonline.exceptions.UserNameAlreadyExists;
import com.seboonline.mappers.UserMapper;
import com.seboonline.models.User;
import com.seboonline.repositories.UserRepository;
import com.seboonline.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(User user) {
        checkIfUserAlreadyExists(user);
        return userMapper.to(this.userRepository.save(user));
    }

    @Override
    public Optional<User> findByName(String userName) {
        return this.userRepository.findByName(userName);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void checkIfUserAlreadyExists(User user) {
        if (this.userRepository.existsByUserName(user.getUsername())){
            throw new UserNameAlreadyExists(user.getUsername());
        }
    }
}
