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
import org.example.blog.service.impl.UserServiceImpl;
import org.example.blog.servlets.post.UpdatePostServlet;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet LoginServlet.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet LoginServlet.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doPost LoginServlet.");
        String userName = req.getParameter("name");
        String userPassword = req.getParameter("password");
        RequestDispatcher requestDispatcher;
        UserDto userDto = userService.loginUserOrNull(userName, userPassword);
        if (userDto != null) {
            req.getSession().setAttribute("name", userDto.getName());
            req.getSession().setAttribute("loggedUser", userDto.getName());
            resp.sendRedirect(req.getContextPath() + "/welcome");
        } else {
            req.setAttribute("loginAttempt", false);
            requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/login.jsp");
            requestDispatcher.forward(req, resp);
        }
        logger.info("Завершение работы функции doPost LoginServlet.");
    }
}
