package com.example1.demo.service.impl;

import com.example1.demo.dto.Login;
import com.example1.demo.dto.LoginResponce;
import com.example1.demo.dto.UserDto;
import com.example1.demo.entity.User;
import com.example1.demo.repository.UserRepo;
import com.example1.demo.security.JwtUtil;
import com.example1.demo.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public UserRegisterServiceImpl(UserRepo userRepo,
                                   PasswordEncoder passwordEncoder,
                                   JwtUtil jwtUtil,
                                   AuthenticationManager authenticationManager,
                                   UserDetailsService userDetailsService) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public LoginResponce registerUser(UserDto userDto) {
        // Check if user already exists
        if (userRepo.findByUserName(userDto.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Encode password and save user
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User savedUser = userRepo.save(new User(userDto.getUserName(), encodedPassword, userDto.getRole()));

        // Generate JWT token
        String token = jwtUtil.generateToken(savedUser.getUserName());
        return new LoginResponce(token);
    }

    @Override
    public LoginResponce login(Login login) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password");
        }

        // Load user details and generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return new LoginResponce(token);
    }
}
