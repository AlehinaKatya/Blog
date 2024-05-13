package org.example.blog.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.dto.UserDto;
import org.example.blog.entities.User;
import org.example.blog.mapper.UserMapper;
import org.example.blog.repository.UserRepository;
import org.example.blog.repository.impl.UserRepositoryImpl;
import org.example.blog.service.UserService;
import org.example.blog.validators.UserValidator;

public class UserServiceImpl implements UserService {
    public UserRepository userRepository = new UserRepositoryImpl(new DatabaseConnection());

    public static Logger logger = LogManager.getLogger(UserService.class);


    @Override
    public UserDto getUserById(Long id) {
        logger.info("Вызвана функция getUserById UserServiceImpl.");
        return UserMapper.MapToDto(userRepository.findById(id));
    }

    @Override
    public boolean createUser(UserDto user) {
        logger.info("Вызвана функция createUser UserServiceImpl.");
        UserValidator.validate(user);
        user.setPassword(getPasswordHash(user.getPassword()));
        return userRepository.save(UserMapper.MapToEntity(user));
    }

    @Override
    public UserDto loginUserOrNull(String name, String password) {
        logger.info("Вызвана функция loginUserOrNull UserServiceImpl.");
        UserDto userDto = UserMapper.MapToDto(userRepository.findByName(name));
        if (userDto != null &&
                userDto.getPassword().equals(getPasswordHash(password))) {
            logger.info("Пользователь успешно залогировался.");
            return userDto;
        }
        logger.info("Возвращен Null.");
        return null;
    }

    private static String getPasswordHash(String password) {
        return String.valueOf(password.hashCode());
    }

    @Override
    public UserDto findByName(String name) {
        logger.info("Вызвана функция findByName UserServiceImpl.");
        return UserMapper.MapToDto(userRepository.findByName(name));
    }

    @Override
    public void updateUser(Long id, String name, String email, String password) {
        logger.info("Вызвана функция updateUser UserServiceImpl.");
        User user = userRepository.findById(id);
        if (user != null) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        }
    }

    @Override
    public void deleteUser(Long id) {
        logger.info("Вызвана функция deleteUser UserServiceImpl.");
        userRepository.delete(userRepository.findById(id));
    }
}
