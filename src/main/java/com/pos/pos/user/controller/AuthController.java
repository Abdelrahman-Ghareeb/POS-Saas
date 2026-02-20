package com.pos.pos.user.controller;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.dtos.response.AuthResponse;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto request) throws UserException {
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserDto request) throws UserException {
        return ResponseEntity.ok(authService.register(request));
    }

}
