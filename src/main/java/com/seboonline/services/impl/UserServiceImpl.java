package com.seboonline.services.impl;

import com.seboonline.dtos.SignUpResponseDto;
import com.seboonline.dtos.UserDto;
import com.seboonline.dtos.UserUpdateDto;
import com.seboonline.exceptions.UserNameAlreadyExistsException;
import com.seboonline.exceptions.UserNotFoundException;
import com.seboonline.mappers.UserMapper;
import com.seboonline.models.User;
import com.seboonline.repositories.UserRepository;
import com.seboonline.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public SignUpResponseDto save(User user) {
        checkIfUserNameAlreadyExists(user.getUsername());
        return userMapper.toSignUpResponse(this.userRepository.save(user));
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        User user = findByUserName(userName);
        checkIfUserIsDeleted(user);
        return this.userMapper.to(user);
    }

    @Override
    public UserDto updateUser(User user, UserUpdateDto userUpdateDto) {
        if (!user.getUsername().equals(userUpdateDto.getUserName())){
            checkIfUserNameAlreadyExists(userUpdateDto.getUserName());
        }
        user.setName(userUpdateDto.getName());
        user.setUserName(userUpdateDto.getUserName());
        user.setActive(userUpdateDto.getActive());
        return this.userMapper.to(this.userRepository.save(user));
    }

    @Override
    public void deleteUser(User user){
        user.setDeleted(true);
        this.userRepository.save(user);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return userName -> userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void checkIfUserNameAlreadyExists(String userName) {
        if (this.userRepository.existsByUserName(userName)){
            throw new UserNameAlreadyExistsException(userName);
        }
    }

    @Override
    public User findByUserName(String userName) {
        return this.userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException(userName));
    }

    private void checkIfUserIsDeleted(User user) {
        if(isUserDeleted(user)){
            throw new UserNotFoundException(user.getUsername());
        }
    }

    private Boolean isUserDeleted(User user) {
        return user.getDeleted();
    }

}
