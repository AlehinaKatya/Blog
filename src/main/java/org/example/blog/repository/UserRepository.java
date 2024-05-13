package org.example.blog.repository;

import org.example.blog.entities.User;

import java.util.List;

public interface UserRepository {
    User findById(Long Id);
    List<User> getAll();
    User findByName(String name);
    boolean save(User user);
    void update(User user);
    void delete(User user);
}
