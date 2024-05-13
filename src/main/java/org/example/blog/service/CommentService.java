package org.example.blog.service;


import org.example.blog.dto.CommentDto;
import org.example.blog.entities.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAllByUserId(Long userId);

    List<CommentDto> findAllByPostId(Long postId);

    boolean createComment(CommentDto comment);

    CommentDto findCommentById(Long id);

    List<CommentDto> getAllComments();

    boolean updateComment(Long id, String content);

    boolean deleteComment(Long id);
}
