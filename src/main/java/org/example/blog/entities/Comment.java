package org.example.blog.entities;

import java.time.LocalDate;

public class Comment {
    private Long id;
    private String content;
    private final Long postId;
    private final LocalDate dateOfPublication;
    private final Long userId;

    public Comment(Long id, String content, Long postId, LocalDate dateOfPublication, Long userId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.dateOfPublication = dateOfPublication;
        this.userId = userId;
    }

    public Comment(String content, Long postId, LocalDate dateOfPublication, Long userId) {
        this.content = content;
        this.postId = postId;
        this.dateOfPublication = dateOfPublication;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", postId=" + postId +
                ", dateOfPublication=" + dateOfPublication +
                ", userId=" + userId +
                '}';
    }

    public String toTestString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", postId=" + postId +
                ", dateOfPublication=" + dateOfPublication +
                ", userId=" + userId +
                '}';
    }
}
