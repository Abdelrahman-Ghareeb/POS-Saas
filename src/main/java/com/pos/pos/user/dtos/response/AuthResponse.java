package com.pos.pos.user.dtos.response;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String massage;
    private UserDto user;
}
