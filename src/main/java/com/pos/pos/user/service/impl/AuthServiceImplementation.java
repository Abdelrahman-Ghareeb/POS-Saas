package com.pos.pos.user.service.impl;

import com.pos.pos.configration.JwtProvider;
import com.pos.pos.user.CustomUserImplementation;
import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.dtos.response.AuthResponse;
import com.pos.pos.user.entity.User;
import com.pos.pos.user.entity.UserRole;
import com.pos.pos.user.exception.UserException;
import com.pos.pos.user.mapper.UserMapper;
import com.pos.pos.user.repo.UserRepository;
import com.pos.pos.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private  final CustomUserImplementation customUserImplementation;
    @Override
    public AuthResponse login(UserDto request) throws UserException {

        Authentication authentication = authenticate(request.getEmail(), request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String token = jwtProvider.generateToken(authentication);
        String role = authorities.iterator().next().getAuthority();
        User user = userRepository.findByEmail(request.getEmail());
        user.setLastLoginAt(LocalDateTime.now());
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMassage("Login successful");
        authResponse.setUser(UserMapper.mapToDTO(user));

        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if(userDetails == null){
            throw new UserException("User Not Found " + email);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Invalid email or password");
        }


        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public AuthResponse register(UserDto request) throws UserException {


        User user = userRepository.findByEmail(request.getEmail());
//        if(user == null){
//            throw new UserException("Invalid email or password");
//        }

        if (request.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("Admin login is not allowed");
        }
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setPhone(request.getPhone());
        newUser.setUsername(request.getUsername());
        newUser.setRole(request.getRole());
        newUser.setFullName(request.getFullName());
        newUser.setLastLoginAt(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser=userRepository.save(newUser);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setMassage("Login successful");
        authResponse.setUser(UserMapper.mapToDTO(savedUser));

        return authResponse;
    }
}
