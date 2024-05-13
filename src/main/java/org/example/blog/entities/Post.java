package org.example.blog.entities;

import java.time.LocalDate;

public class Post {
    private Long id;
    private String header;
    private String content;
    private final LocalDate dateOfPublication;
    private final Long userId;

    public Post(Long id, String header, String content, LocalDate dateOfPublication, Long userId) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
        this.userId = userId;
    }

    public Post(String header, String content, LocalDate dateOfPublication, Long userId) {
        this.header = header;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public Long getUserId() {
        return userId;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", header='" + header + '\'' +
                ", content='" + content + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", userId=" + userId +
                '}';
    }

    public String toTestString() {
        return "Post{" +
                "header='" + header + '\'' +
                ", content='" + content + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", userId=" + userId +
                '}';
    }
}
