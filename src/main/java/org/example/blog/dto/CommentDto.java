package org.example.blog.dto;

import java.time.LocalDate;

public class CommentDto {
    private Long id;
    private String content;
    private final Long postId;
    private final LocalDate dateOfPublication;
    private final String userName;

    public CommentDto(Long id, String content, Long postId, LocalDate dateOfPublication, String userName) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.dateOfPublication = dateOfPublication;
        this.userName = userName;
    }

    public CommentDto(String content, Long postId, LocalDate dateOfPublication, String userName) {
        this.content = content;
        this.postId = postId;
        this.dateOfPublication = dateOfPublication;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public String getUserName() {
        return userName;
    }
}
