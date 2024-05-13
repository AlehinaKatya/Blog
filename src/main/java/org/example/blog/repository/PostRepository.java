package org.example.blog.repository;

import org.example.blog.entities.Post;

import java.util.List;

public interface PostRepository {
    Post findById(Long Id);
    List<Post> getAll();
    List<Post> findAllByUserId(Long userId);
    List<Post> findByHeader(String header);
    boolean save(Post post);
    boolean update(Post post);
    boolean delete(Post post);
}
