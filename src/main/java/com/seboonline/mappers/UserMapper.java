package com.seboonline.mappers;

import com.seboonline.dtos.UserDto;
import com.seboonline.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto to(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getActive()
        );
    }
}
