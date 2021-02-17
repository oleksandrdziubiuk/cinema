package com.dev.cinema.controller;

import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.dtomapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService, UserMapper userMapper) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto requestDto) {
        authService.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
