package org.example.blog.repository.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.entities.Comment;
import org.example.blog.repository.CommentRepository;
import org.example.blog.repository.PostRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {
    private final Connection connection;
    private static final Logger logger = LogManager.getLogger(CommentRepository.class);

    public CommentRepositoryImpl(DatabaseConnection datebaseConnection) {
        this.connection = datebaseConnection.getConnection();
    }

    @Override
    public Comment findById(Long Id) {
        logger.info("Вызвана функция findById CommentRepository.");
        Comment comment = null;
        String commentQuery = "SELECT id, content, id_post, date_publication, id_user" +
                " FROM comments WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);

            preparedStatement.setLong(1, Id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                comment = new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getLong("id_post"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));
            }
            logger.info("Комментарий получен по id.");
        } catch (SQLException e) {
            logger.error("Ошибка получения комментария по id:\n" + e.getMessage());
        }
        return comment;
    }

    @Override
    public List<Comment> getAll() {
        logger.info("Вызвана функция getAll CommentRepository.");
        List<Comment> commentList = new ArrayList<>();
        String commentQuery = "SELECT id, content, id_post, date_publication, id_user" +
                " FROM comments";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getLong("id_post"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                commentList.add(comment);
            }
            logger.info("Успешно получены все комментарии.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех комментариев:\n" + e.getMessage());
        }
        return commentList;
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        logger.info("Вызвана функция findAllByPostId CommentRepository.");
        List<Comment> commentList = new ArrayList<>();
        String commentQuery = "SELECT id, content, id_post, date_publication, id_user" +
                " FROM comments WHERE id_post = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);
            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getLong("id_post"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                commentList.add(comment);
            }
            logger.info("Успешно получены все комментарии по id поста.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех комментариев по id поста:\n" + e.getMessage());
        }
        return commentList;
    }

    @Override
    public List<Comment> findAllByUserId(Long userId) {
        logger.info("Вызвана функция findAllByUserId CommentRepository.");
        List<Comment> commentList = new ArrayList<>();
        String commentQuery = "SELECT id, content, id_post, date_publication, id_user" +
                " FROM comments WHERE id_user = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment(
                        resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getLong("id_post"),
                        resultSet.getDate("date_publication").toLocalDate(),
                        resultSet.getLong("id_user"));

                commentList.add(comment);
            }
            logger.info("Успешно получены все комментарии по id пользователя.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех комментариев по id пользователя:\n" + e.getMessage());
        }
        return commentList;
    }

    @Override
    public boolean save(Comment comment) {
        logger.info("Вызвана функция save CommentRepository.");
        String commentQuery = "INSERT INTO comments(content, id_post, date_publication, id_user)"
                + "VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);

            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setDate(3, Date.valueOf(comment.getDateOfPublication()));
            preparedStatement.setLong(4, comment.getUserId());

            logger.info("Новый комментарий успешно добавлен.");

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка добавления комментария:\n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Comment comment) {
        logger.info("Вызвана функция update CommentRepository.");
        String commentQuery =
                "UPDATE comments SET content = ?,"
                        + "id_post = ?,"
                        + "date_publication = ?, "
                        + "id_user = ? "
                        + "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);

            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setDate(3, Date.valueOf(comment.getDateOfPublication()));
            preparedStatement.setLong(4, comment.getUserId());
            preparedStatement.setLong(5, comment.getId());

            logger.info("Комментарий успешно изменен.");

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка изменения комментария:\n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Comment comment) {
        logger.info("Вызвана функция delete CommentRepository.");
        String commentQuery = "DELETE FROM comments WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(commentQuery);
            preparedStatement.setLong(1, comment.getId());
            logger.info("Комментарий успешно удален.");
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка удаления комментария:\n" + e.getMessage());
            return false;
        }
    }
}
