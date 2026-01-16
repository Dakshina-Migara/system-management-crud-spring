package com.example1.demo.service;

import com.example1.demo.dto.Login;
import com.example1.demo.dto.LoginResponce;
import com.example1.demo.dto.UserDto;

public interface UserRegisterService {

    // Register user and automatically return JWT token
    LoginResponce registerUser(UserDto userDto);

    // Authenticate user and return JWT token
    LoginResponce login(Login login);
}
