package org.example.blog.dto;

import java.time.LocalDate;

public class PostDto {
    private Long id;
    private String header;
    private String content;
    private final LocalDate dateOfPublication;
    private final String userName;

    public PostDto(Long id, String header, String content, LocalDate dateOfPublication, String userName) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
        this.userName = userName;
    }

    public PostDto(String header, String content, LocalDate dateOfPublication, String userName) {
        this.header = header;
        this.content = content;
        this.dateOfPublication = dateOfPublication;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
