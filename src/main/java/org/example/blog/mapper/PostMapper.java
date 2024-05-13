package org.example.blog.mapper;


import org.example.blog.DatabaseConnection;
import org.example.blog.dto.PostDto;
import org.example.blog.entities.Post;
import org.example.blog.repository.UserRepository;
import org.example.blog.repository.impl.UserRepositoryImpl;

public class PostMapper {
    private static final UserRepository userRepository = new UserRepositoryImpl(new DatabaseConnection());
    public static PostDto MapToDto(Post post) {
        if (post == null) {
            return null;
        } else {
            return new PostDto(
                    post.getId(),
                    post.getHeader(),
                    post.getContent(),
                    post.getDateOfPublication(),
                    userRepository.findById(post.getUserId()).getName()
            );
        }
    }

    public static Post MapToEntity(PostDto postDto) {
        if (postDto == null) {
            return null;
        } else {
            return new Post(
                    postDto.getId(),
                    postDto.getHeader(),
                    postDto.getContent(),
                    postDto.getDateOfPublication(),
                    userRepository.findByName(postDto.getUserName()).getId()
            );
        }
    }
}
