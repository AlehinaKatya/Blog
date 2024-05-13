package org.example.blog.mapper;


import org.example.blog.DatabaseConnection;
import org.example.blog.dto.CommentDto;
import org.example.blog.entities.Comment;
import org.example.blog.repository.UserRepository;
import org.example.blog.repository.impl.UserRepositoryImpl;

public class CommentMapper {
    private static final UserRepository userRepository = new UserRepositoryImpl(new DatabaseConnection());
    public static CommentDto MapToDto(Comment comment) {
        if (comment == null) {
            return null;
        } else {
            return new CommentDto(
                    comment.getId(),
                    comment.getContent(),
                    comment.getPostId(),
                    comment.getDateOfPublication(),
                    userRepository.findById(comment.getUserId()).getName()
            );
        }
    }

    public static Comment MapToEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        } else {
            return new Comment(
                    commentDto.getId(),
                    commentDto.getContent(),
                    commentDto.getPostId(),
                    commentDto.getDateOfPublication(),
                    userRepository.findByName(commentDto.getUserName()).getId()
            );
        }
    }
}
