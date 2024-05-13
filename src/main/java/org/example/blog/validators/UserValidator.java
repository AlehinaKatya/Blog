package org.example.blog.validators;

import org.example.blog.dto.UserDto;

import java.util.regex.Pattern;

public class UserValidator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    public static void validate(UserDto user) {
        if (user.getName() == null) {
            throw new IllegalArgumentException("Name is required");
        }
        if (user.getName().length() < 3) {
            throw new IllegalArgumentException("Name is too short");
        }
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Wrong email format");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email is required");
        }
        if (user.getPassword().length() < 5) {
            throw new IllegalArgumentException("Password is too short");
        }
    }
}
