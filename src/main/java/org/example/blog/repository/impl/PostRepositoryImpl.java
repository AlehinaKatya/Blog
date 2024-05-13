package org.example.blog.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.entities.Post;
import org.example.blog.repository.PostRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {
    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(PostRepository.class);

    public PostRepositoryImpl(DatabaseConnection datebaseConnection) {
        this.connection = datebaseConnection.getConnection();
    }

    @Override
    public Post findById(Long Id) {
        logger.info("Вызвана функция findById PostRepository.");
        Post post = null;
        String postQuery = "SELECT id, header, content, date_publication, id_user" +
                " FROM posts WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);

            preparedStatement.setLong(1, Id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("header"),
                        resultSet.getString("content"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));
            }
            logger.info("Пост успешно получен по id.");
        } catch (SQLException e) {
            logger.error("Ошибка получения поста по id:\n" + e.getMessage());
        }
        return post;
    }

    @Override
    public List<Post> getAll() {
        logger.info("Вызвана функция getAll PostRepository.");
        List<Post> postList = new ArrayList<>();
        String postQuery = "SELECT id, header, content, date_publication, id_user" +
                " FROM posts";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("header"),
                        resultSet.getString("content"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                postList.add(post);
            }
            logger.info("Все посты успешно получены.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех постов:\n" + e.getMessage());
        }
        return postList;
    }

    @Override
    public List<Post> findAllByUserId(Long userId) {
        logger.info("Вызвана функция findAllByUserId PostRepository.");
        List<Post> postList = new ArrayList<>();
        String postQuery = "SELECT id, header, content, date_publication, id_user" +
                " FROM posts WHERE id_user = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("header"),
                        resultSet.getString("content"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                postList.add(post);
            }
            logger.info("Успешно получены все посты по id пользователя.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех постов по id пользователя:\n" + e.getMessage());
        }
        return postList;
    }

    @Override
    public List<Post> findByHeader(String header) {
        logger.info("Вызвана функция findByHeader PostRepository.");
        List<Post> postList = new ArrayList<>();
        String postQuery = "SELECT id, header, content, date_publication, id_user" +
                " FROM posts WHERE header = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);

            preparedStatement.setString(1, header);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Post post = new Post(
                        resultSet.getLong("id"),
                        resultSet.getString("header"),
                        resultSet.getString("content"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                postList.add(post);
            }
            logger.info("Успешно получены все посты по названию.");
        } catch (SQLException e) {
            logger.error("Ошибка нахождения постов по названию:\n" + e.getMessage());
        }
        return postList;
    }

    @Override
    public boolean save(Post post) {
        logger.info("Вызвана функция save PostRepository.");
        String postQuery = "INSERT INTO posts(header, content, date_publication, id_user)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);

            preparedStatement.setString(1, post.getHeader());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setDate(3, Date.valueOf(post.getDateOfPublication()));
            preparedStatement.setLong(4, post.getUserId());

            logger.info("Новый пост успешно сохранен.");

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка добавления поста:\n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Post post) {
        logger.info("Вызвана функция update PostRepository.");
        String userQuery =
                "UPDATE posts SET header = ?,"
                        + "content = ?,"
                        + "date_publication = ?, "
                        + "id_user = ? "
                        + "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, post.getHeader());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setDate(3, Date.valueOf(post.getDateOfPublication()));
            preparedStatement.setLong(4, post.getUserId());
            preparedStatement.setLong(5, post.getId());

            logger.info("Пост успешно обновлен.");

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка изменения поста:\n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Post post) {
        logger.info("Вызвана функция delete UserRepository.");
        String postQuery = "DELETE FROM posts WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(postQuery);
            preparedStatement.setLong(1, post.getId());
            logger.info("Пост успешно удален.");
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка удаления поста:\n" + e.getMessage());
            return false;
        }
    }
}
