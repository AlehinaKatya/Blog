package org.example.blog.servlets.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.dto.UserDto;
import org.example.blog.entities.User;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/signup")
public class SingUpServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();

    private static final Logger logger = LogManager.getLogger(SingUpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet SingUpServlet.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/signup.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet SingUpServlet.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doPost SingUpServlet.");
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            boolean created = userService.createUser(new UserDto(name, email, password));
            req.setAttribute("userCreated", created);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/signup.jsp");
            requestDispatcher.forward(req, resp);
            logger.info("Новый пользователь успешно создан.");
        } catch (Exception e ){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/error.jsp");
            requestDispatcher.forward(req, resp);
            logger.error("Ошибка при создании пользователя.");
        }

        logger.info("Завершение работы функции doPost SingUpServlet.");
    }
}
