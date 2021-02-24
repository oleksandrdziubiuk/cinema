package com.dev.theatre.controller;

import com.dev.theatre.model.dto.UserRequestDto;
import com.dev.theatre.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserRequestDto requestDto) {
        authService.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
