package org.example.blog.mapper;

import org.example.blog.dto.UserDto;
import org.example.blog.entities.User;

public class UserMapper {
    public static UserDto MapToDto(User user) {
        if (user == null) {
            return null;
        } else {
            return new UserDto(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPassword()
            );
        }
    }

    public static User MapToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        } else {
            return new User(
                    userDto.getId(),
                    userDto.getName(),
                    userDto.getEmail(),
                    userDto.getPassword()
            );
        }
    }
}
