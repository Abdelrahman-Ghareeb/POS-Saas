package com.pos.pos.user.service.impl;

import com.pos.pos.configration.JwtProvider;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.repo.UserRepository;
import com.pos.pos.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public User getUserByEmail(String email) throws UserException {


        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("User Not Found with email: " + email);
        }

        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
            if(user == null){
                throw new UserException("User Not Found with email: " + email);
            }
            return user;

    }

    @Override
    public User getUserFromToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("User Not Found with email: " + email);
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
       return userRepository.findById(id).orElseThrow(()-> new UserException("User not found"));
    }

    @Override
    public List<User> ListAllUsers() {
        return userRepository.findAll();
    }
}
