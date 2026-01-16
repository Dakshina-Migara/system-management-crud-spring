package com.example1.demo.controller;

import com.example1.demo.dto.Login;
import com.example1.demo.dto.LoginResponce;
import com.example1.demo.dto.UserDto;
import com.example1.demo.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    @Autowired
    public UserRegisterController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    // Register user → auto JWT
    @PostMapping("/register")
    public ResponseEntity<LoginResponce> register(@RequestBody UserDto user) {
        LoginResponce token = userRegisterService.registerUser(user);
        return ResponseEntity.ok(token);
    }
}
