package com.example1.demo.controller;

import com.example1.demo.dto.Login;
import com.example1.demo.dto.LoginResponce;
import com.example1.demo.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final UserRegisterService userRegisterService;

    @Autowired
    public LoginController(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponce> login(@RequestBody Login loginRequest) {
        // Call service to authenticate and return JWT
        LoginResponce token = userRegisterService.login(loginRequest);
        return ResponseEntity.ok(token);
    }
}
