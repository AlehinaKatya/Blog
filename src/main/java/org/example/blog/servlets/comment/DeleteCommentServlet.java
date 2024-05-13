package org.example.blog.servlets.comment;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.blog.dto.UserDto;
import org.example.blog.service.impl.CommentServiceImpl;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/delete_comment")
public class DeleteCommentServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostServiceImpl postService = new PostServiceImpl();
    private final CommentServiceImpl commentService = new CommentServiceImpl();

    private static final Logger logger = LogManager.getLogger(DeleteCommentServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet DeleteCommentServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        Long id = Long.valueOf(req.getParameter("id"));
        boolean deleted = commentService.deleteComment(id);
        req.setAttribute("commentDeleted", deleted);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/comment/delete_comment.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet DeleteCommentServlet.");
    }
}

