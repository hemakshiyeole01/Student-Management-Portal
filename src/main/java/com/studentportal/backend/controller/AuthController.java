package com.studentportal.backend.controller;

import com.studentportal.backend.dto.auth.LoginRequest;
import com.studentportal.backend.dto.auth.LoginResponse;
import com.studentportal.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authService.login(request);
    }
}