package org.example.blog.repository;

import org.example.blog.entities.Comment;

import java.util.List;

public interface CommentRepository {
    Comment findById(Long Id);
    List<Comment> getAll();
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByUserId(Long userId);
    boolean save(Comment comment);
    boolean update(Comment comment);
    boolean delete(Comment comment);
}
