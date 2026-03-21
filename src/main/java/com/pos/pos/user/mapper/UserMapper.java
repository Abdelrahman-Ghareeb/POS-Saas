package com.pos.pos.user.mapper;

import com.pos.pos.user.dtos.UserDto;
import com.pos.pos.user.entity.User;

public class UserMapper {
    public static UserDto mapToDTO(User savedUser) {

        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setUsername(savedUser.getUsername());
        userDto.setEmail(savedUser.getEmail());
        userDto.setPassword(savedUser.getPassword());
        userDto.setFullName(savedUser.getFullName());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());
        userDto.setLastLoginAt(savedUser.getLastLoginAt());
        userDto.setBranchId(savedUser.getBranch() != null ?savedUser.getBranch().getId():null);
        userDto.setStoreId(savedUser.getStore() != null ?savedUser.getStore().getId():null);
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
//        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());
        user.setRole(userDto.getRole());
        user.setPhone(userDto.getPhone());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());
        user.setLastLoginAt(userDto.getLastLoginAt());
        return user;
    }
}
