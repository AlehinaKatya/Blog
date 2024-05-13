package org.example.blog.service.impl;

import org.example.blog.dto.UserDto;
import org.example.blog.entities.User;
import org.example.blog.mapper.UserMapper;
import org.example.blog.repository.impl.UserRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById() {
    }


    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto(1L, "test1", "test@mail.ru", "test1234");
        when(userRepository.save(UserMapper.MapToEntity(userDto))).thenReturn(true);

        boolean result = userService.createUser(userDto);

        Assertions.assertTrue(result);
    }

    //public void testLoginUserOrNull() {
    //}

    @Test
    public void testFindByName() {
        User user = new User(1L, "test1", "test@mail.ru", "test1234");
        when(userRepository.findByName("test1")).thenReturn(user);

        UserDto result = userService.findByName("test1");

        assertThat(result.getName()).isEqualTo("test1");
    }

    @Test
    public void testUpdateUser() {
        //UserDto userDto = new UserDto(1L, "test1", "test@mail.ru", "test1234");
        //User user = new User(1L, "test1", "test@mail.ru", "test1234");
        //when(UserMapper.MapToEntity(userDto)).thenReturn(user);

        //userService.updateUser(1L, "test1", "test1@mail.ru", "test1");

        //verify(userRepository).update(user);
    }

    //public void testDeleteUser() {
    //}
}