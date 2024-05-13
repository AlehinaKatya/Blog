package org.example.blog.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.dto.PostDto;
import org.example.blog.entities.Post;
import org.example.blog.mapper.PostMapper;
import org.example.blog.repository.PostRepository;
import org.example.blog.repository.impl.PostRepositoryImpl;
import org.example.blog.service.PostService;
import org.example.blog.validators.PostValidator;

import java.util.ArrayList;
import java.util.List;

public class PostServiceImpl implements PostService {
    public PostRepository postRepository = new PostRepositoryImpl(new DatabaseConnection());

    public static Logger logger = LogManager.getLogger(PostService.class);


    @Override
    public List<PostDto> findAllByUserId(Long userId) {
        logger.info("Вызвана функция findAllByUserId PostServiceImpl.");
        List<Post> allPostByUserId = postRepository.findAllByUserId(userId);
        List<PostDto> allPostDtoByUserId = new ArrayList<>();
        for (Post post: allPostByUserId) {
            allPostDtoByUserId.add(PostMapper.MapToDto(post));
        }
        return allPostDtoByUserId;
    }

    @Override
    public List<PostDto> findByHeader(String header) {
        logger.info("Вызвана функция findByHeader PostServiceImpl.");
        List<Post> allPostByHeader = postRepository.findByHeader(header);
        List<PostDto> allPostDtoByHeader = new ArrayList<>();
        for (Post post: allPostByHeader) {
            allPostDtoByHeader.add(PostMapper.MapToDto(post));
        }
        return allPostDtoByHeader;
    }

    @Override
    public boolean createPost(PostDto post) {
        logger.info("Вызвана функция createPost PostServiceImpl.");
        PostValidator.validate(post);
        return postRepository.save(PostMapper.MapToEntity(post));
    }

    @Override
    public PostDto findPostById(Long id) {
        logger.info("Вызвана функция findPostById PostServiceImpl.");
        return PostMapper.MapToDto(postRepository.findById(id));
    }

    @Override
    public List<PostDto> getAllPosts() {
        logger.info("Вызвана функция getAllPosts PostServiceImpl.");
        List<Post> allPost = postRepository.getAll();
        List<PostDto> allPostDto = new ArrayList<>();
        for (Post post: allPost) {
            allPostDto.add(PostMapper.MapToDto(post));
        }
        return allPostDto;
    }

    @Override
    public boolean updatePost(Long id, String header, String content) {
        logger.info("Вызвана функция updatePost PostServiceImpl.");
        PostDto post = PostMapper.MapToDto(postRepository.findById(id));
        PostValidator.validate(post);
        if (post != null) {
            post.setHeader(header);
            post.setContent(content);
        }
        return postRepository.update(PostMapper.MapToEntity(post));
    }

    @Override
    public boolean deletePost(Long id) {
        logger.info("Вызвана функция deletePost PostServiceImpl.");
        return postRepository.delete(postRepository.findById(id));
    }
}
