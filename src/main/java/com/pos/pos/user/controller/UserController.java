package com.pos.pos.user.controller;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.mapper.UserMapper;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String token) throws UserException {

        User user = userService.getUserFromToken(token);
        return ResponseEntity.ok(UserMapper.mapToDTO(user));
    }



    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) throws UserException {

        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.mapToDTO(user));
    }
}
