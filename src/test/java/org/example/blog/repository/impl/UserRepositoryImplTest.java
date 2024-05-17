package org.example.blog.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.example.blog.entities.User;
import org.example.blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserRepositoryImplTest {
    @Mock
    private UserRepository userRepository;


    @Test
    public void testFindById() {

        User user = new User(1L, "test", "test@mail.ru", "test");

        when(userRepository.findById(1L)).thenReturn(user);
        User foundUser = userRepository.findById(1L);

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void testGetAll() {
        User user1 = new User(1L, "test1", "test1@mail.ru", "test1");
        User user2 = new User(2L,"test2", "test2@mail.ru", "test2");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.getAll()).thenReturn(users);

        List<User> usersFromRepository = userRepository.getAll();

        assertEquals(users.size(), usersFromRepository.size());
    }

    @Test
    public void testFindByName() {
        User user = new User(1L, "test", "test@mail.ru", "test");

        when(userRepository.findByName("test")).thenReturn(user);
        User foundUser = userRepository.findByName("test");

        assertEquals(user.getId(), foundUser.getId());
        assertEquals(user.getName(), foundUser.getName());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void testSave() {
        User user = new User(1L,"test", "test@mail.ru", "test");

        when(userRepository.save(user)).thenReturn(true);

        boolean created = userRepository.save(user);

        assertTrue(created);
    }
}