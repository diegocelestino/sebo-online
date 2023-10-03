package com.seboonline.services.impl;

import com.seboonline.dtos.UserDto;
import com.seboonline.mappers.UserMapper;
import com.seboonline.repositories.UserRepository;
import com.seboonline.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userMapper.to(userRepository.findAll());
    }
}
