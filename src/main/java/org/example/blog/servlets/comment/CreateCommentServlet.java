package org.example.blog.servlets.comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.dto.CommentDto;
import org.example.blog.dto.UserDto;
import org.example.blog.entities.Comment;
import org.example.blog.service.UserService;
import org.example.blog.service.impl.CommentServiceImpl;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/create_comment")
public class CreateCommentServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostServiceImpl postService = new PostServiceImpl();
    private final CommentServiceImpl commentService = new CommentServiceImpl();

    private static final Logger logger = LogManager.getLogger(CreateCommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet CreateCommentServlet.");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/comment/create_comment.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet CreateCommentServlet.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doPost CreateCommentServlet.");
        try {
            String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
            UserDto user = userService.findByName(name);
            req.setAttribute("user", user);

            Long post_id = Long.valueOf(req.getParameter("post_id"));

            String content = req.getParameter("content");
            boolean created = commentService.createComment(
                    new CommentDto(content, post_id, LocalDate.now(), name));
            req.setAttribute("commentCreated", created);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/comment/create_comment.jsp");
            requestDispatcher.forward(req, resp);
            logger.info("Новый комментарий успешно добавлен.");
        } catch (Exception e ){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/error.jsp");
            requestDispatcher.forward(req, resp);
            logger.error("Ошибка при создании поста.");
        }
        logger.info("Завершение работы функции doPost CreateCommentServlet.");
    }
}
