package com.pos.pos.user.service;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.dtos.response.AuthResponse;
import com.pos.pos.user.exception.UserException;

public interface AuthService {

    AuthResponse login(UserDto request) throws UserException;
    AuthResponse register(UserDto request) throws UserException;

}
