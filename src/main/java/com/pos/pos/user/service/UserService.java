package com.pos.pos.user.service;

import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;

import java.util.List;

public interface UserService {


    public User getUserByEmail(String email) throws UserException;
    public User getCurrentUser() throws UserException;
    public User getUserFromToken(String token) throws UserException;
    public User getUserById(Long id) throws UserException;
    public List<User> ListAllUsers();
}
