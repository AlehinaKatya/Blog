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
import org.example.blog.dto.PostDto;
import org.example.blog.dto.UserDto;
import org.example.blog.service.CommentService;
import org.example.blog.service.PostService;
import org.example.blog.service.UserService;
import org.example.blog.service.impl.CommentServiceImpl;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/post_comments")
public class AllPostCommentsServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    PostService postService = new PostServiceImpl();
    CommentService commentService = new CommentServiceImpl();

    private static final Logger logger = LogManager.getLogger(AllPostCommentsServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet AllPostCommentsServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);
        req.setAttribute("userName", user.getName());

        Long post_id = Long.valueOf(req.getParameter("post_id"));
        PostDto post = postService.findPostById(post_id);
        req.setAttribute("post_id", post_id);
        req.setAttribute("header", post.getHeader());
        req.setAttribute("content", post.getContent());

        List<CommentDto> commentList = commentService.findAllByPostId(post_id);
        req.setAttribute("commentList", commentList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/comment/post_comments.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet AllPostCommentsServlet.");
    }
}
