package org.example.blog.servlets.post;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.dto.PostDto;
import org.example.blog.dto.UserDto;
import org.example.blog.entities.Post;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/create_post")
public class CreatePostServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostServiceImpl postService = new PostServiceImpl();

    private static final Logger logger = LogManager.getLogger(CreatePostServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet CreatePostServlet.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/create_post.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet CreatePostServlet.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doPost CreatePostServlet.");
        try {
            String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
            UserDto user = userService.findByName(name);
            req.setAttribute("user", user);

            String header = req.getParameter("header");
            String content = req.getParameter("content");
            boolean created = postService.createPost(
                    new PostDto(header, content, LocalDate.now(), name));
            req.setAttribute("postCreated", created);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/create_post.jsp");
            requestDispatcher.forward(req, resp);
            logger.info("Новый пост успешно создан.");
        } catch (Exception e ){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/error.jsp");
            requestDispatcher.forward(req, resp);
            logger.error("Ошибка при создании поста.");
        }

        logger.info("Завершение работы функции doPost CreatePostServlet.");
    }
}
