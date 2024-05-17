package org.example.blog.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.DatabaseConnection;
import org.example.blog.entities.User;
import org.example.blog.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    private final DatabaseConnection databaseConnection;
    private static final Logger logger = LogManager.getLogger(UserRepository.class);

    public UserRepositoryImpl(DatabaseConnection datebaseConnection) {
        databaseConnection = datebaseConnection;
    }

    @Override
    public User findById(Long Id) {

        logger.info("Вызвана функция findById UserRepository.");
        User user = null;
        String userQuery = "SELECT id, name, email, password" +
                " FROM users WHERE id = ?";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setLong(1, Id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
            logger.info("Пользователь получен по id.");
        } catch (SQLException e) {
            logger.error("Ошибка получения пользователя по id:\n" + e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        logger.info("Вызвана функция getAll UserRepository.");
        List<User> userList = new ArrayList<>();
        String userQuery = "SELECT id, name, email, password" +
                " FROM users";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );

                userList.add(user);
            }
            logger.info("Все пользователи успешно получены.");
        } catch (SQLException e) {
            logger.error("Ошибка получения всех пользователей:\n" + e.getMessage());
        }
        return userList;
    }

    @Override
    public User findByName(String name) {
        logger.info("Вызвана функция findByName UserRepository.");
        User user = null;
        String userQuery = "SELECT id, name, email, password" +
                " FROM users WHERE name = ?";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
            logger.info("Пользователь получен по имени.");
        } catch (SQLException e) {
            logger.error("Ошибка нахождения пользователя по имени:\n" + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean save(User user) {
        logger.info("Вызвана функция save UserRepository.");
        String userQuery = "INSERT INTO users(name, email, password)"
                + "VALUES(?, ?, ?)";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            logger.info("Новый пользователь успешно сохранен.");

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            logger.error("Ошибка добавления пользователя:\n" + e.getMessage());
            return false;
        }
    }

    @Override
    public void update(User user) {
        logger.info("Вызвана функция update UserRepository.");
        String userQuery =
                "UPDATE users SET name = ?,"
                        + "email = ?,"
                        + "password = ? "
                        + "WHERE id = ?";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());

            preparedStatement.executeUpdate();
            logger.info("Пользователь успешно обновлен.");
        } catch (SQLException e) {
            logger.error("Ошибка изменения пользователя:\n" + e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        logger.info("Вызвана функция delete UserRepository.");
        String userQuery = "DELETE FROM users WHERE name = ?";
        try (Connection connection = this.databaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(userQuery);
            preparedStatement.setString(1, user.getName());
            preparedStatement.executeUpdate();
            logger.info("Пользователь успешно удален.");
        } catch (SQLException e) {
            logger.error("Ошибка удаления пользователя:\n" + e.getMessage());
        }
    }
}
