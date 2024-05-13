package org.example.blog.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.dto.CommentDto;
import org.example.blog.entities.Comment;
import org.example.blog.mapper.CommentMapper;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.impl.CommentRepositoryImpl;
import org.example.blog.service.CommentService;
import org.example.blog.validators.CommentValidator;

import java.util.ArrayList;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    public CommentRepository commentRepository = new CommentRepositoryImpl(new DatabaseConnection());

    public static Logger logger = LogManager.getLogger(CommentService.class);

    @Override
    public List<CommentDto> findAllByUserId(Long userId) {
        logger.info("Вызвана функция findAllByUserId CommentServiceImpl.");
        List<Comment> allCommentByUserId = commentRepository.findAllByUserId(userId);
        List<CommentDto> allCommentDtoByUserId = new ArrayList<>();
        for ( Comment comment: allCommentByUserId) {
            allCommentDtoByUserId.add(CommentMapper.MapToDto(comment));
        }
        return allCommentDtoByUserId;
    }

    @Override
    public List<CommentDto> findAllByPostId(Long postId) {
        logger.info("Вызвана функция findAllByPostId CommentServiceImpl.");
        List<Comment> allCommentByPostId = commentRepository.findAllByPostId(postId);
        List<CommentDto> allCommentDtoByPostId = new ArrayList<>();
        for ( Comment comment: allCommentByPostId) {
            allCommentDtoByPostId.add(CommentMapper.MapToDto(comment));
        }
        return allCommentDtoByPostId;
    }

    @Override
    public boolean createComment(CommentDto comment) {
        logger.info("Вызвана функция createComment CommentServiceImpl.");
        CommentValidator.validate(comment);
        return commentRepository.save(CommentMapper.MapToEntity(comment));
    }

    @Override
    public CommentDto findCommentById(Long id) {
        logger.info("Вызвана функция findCommentById CommentServiceImpl.");
        return CommentMapper.MapToDto(commentRepository.findById(id));
    }

    @Override
    public List<CommentDto> getAllComments() {
        logger.info("Вызвана функция getAllComments CommentServiceImpl.");
        List<Comment> allComment = commentRepository.getAll();
        List<CommentDto> allCommentDto = new ArrayList<>();
        for ( Comment comment: allComment) {
            allCommentDto.add(CommentMapper.MapToDto(comment));
        }
        return allCommentDto;
    }

    @Override
    public boolean updateComment(Long id, String content) {
        logger.info("Вызвана функция updateComment CommentServiceImpl.");
        Comment comment = commentRepository.findById(id);
        if (comment != null) {
            comment.setContent(content);
        }
        return commentRepository.update(comment);
    }

    @Override
    public boolean deleteComment(Long id) {
        logger.info("Вызвана функция deleteComment CommentServiceImpl.");
        return commentRepository.delete(commentRepository.findById(id));
    }
}
