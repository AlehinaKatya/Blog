package org.example.blog.service;

import org.example.blog.dto.UserDto;

public interface UserService {
    UserDto getUserById(Long id);
    boolean createUser(UserDto user);

    UserDto loginUserOrNull(String name, String password);

    UserDto findByName(String name);
    void updateUser(Long id, String name, String email, String password);
    void deleteUser(Long id);
}
