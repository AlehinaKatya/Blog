package org.example.blog.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.servlets.user.WelcomeServlet;

import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ErrorServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet ErrorServlet.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/error.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet ErrorServlet.");
    }
}
