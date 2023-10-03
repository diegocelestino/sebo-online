package com.seboonline.services;


import com.seboonline.dtos.UserDto;

import java.util.List;

public interface AdminService {
    List<UserDto> getAllUsers();
}
