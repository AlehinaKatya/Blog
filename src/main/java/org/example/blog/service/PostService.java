package org.example.blog.service;

import org.example.blog.dto.PostDto;
import org.example.blog.entities.Post;

import java.util.List;

public interface PostService {
    List<PostDto> findAllByUserId(Long userId);

    boolean createPost(PostDto post);

    PostDto findPostById(Long id);

    List<PostDto> findByHeader(String header);

    List<PostDto> getAllPosts();

    boolean updatePost(Long id, String header, String content);

    boolean deletePost(Long id);
}
