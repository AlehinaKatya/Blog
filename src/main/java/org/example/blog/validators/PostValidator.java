package org.example.blog.validators;


import org.example.blog.dto.PostDto;

public class PostValidator {
    public static void validate(PostDto post) {
        if (post.getHeader() == null) {
            throw new IllegalArgumentException("Header is required");
        }
        if (post.getHeader().length() < 3) {
            throw new IllegalArgumentException("Header is too short");
        }
        if (post.getContent() == null) {
            throw new IllegalArgumentException("Content is required");
        }
        if (post.getContent().length() < 5) {
            throw new IllegalArgumentException("Content is too short");
        }
    }
}
