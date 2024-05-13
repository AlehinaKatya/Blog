package org.example.blog.servlets.post;

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
import org.example.blog.service.impl.CommentServiceImpl;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/delete_post")
public class DeletePostServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostServiceImpl postService = new PostServiceImpl();
    private final CommentServiceImpl commentService = new CommentServiceImpl();

    private static final Logger logger = LogManager.getLogger(DeletePostServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet DeletePostServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        Long id = Long.valueOf(req.getParameter("id"));
        List<CommentDto> comments = commentService.findAllByPostId(id);
        for (CommentDto comment: comments) {
            commentService.deleteComment(comment.getId());
        }
        boolean deleted = postService.deletePost(id);
        req.setAttribute("postDeleted", deleted);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/delete_post.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet DeletePostServlet.");
    }
}
