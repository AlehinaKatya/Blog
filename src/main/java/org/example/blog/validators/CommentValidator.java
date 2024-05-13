package org.example.blog.validators;

import org.example.blog.dto.CommentDto;
import org.example.blog.dto.PostDto;

public class CommentValidator {
    public static void validate(CommentDto comment) {
        if (comment.getContent() == null) {
            throw new IllegalArgumentException("Content is required");
        }
        if (comment.getContent().length() < 5) {
            throw new IllegalArgumentException("Content is too short");
        }
    }
}
