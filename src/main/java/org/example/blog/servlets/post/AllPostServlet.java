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
import org.example.blog.service.PostService;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;
import org.example.blog.servlets.comment.DeleteCommentServlet;

import java.io.IOException;
import java.util.List;

@WebServlet("/all_posts")
public class AllPostServlet extends HttpServlet {
    private final PostService postService = new PostServiceImpl();
    private final UserServiceImpl userService = new UserServiceImpl();

    private static final Logger logger = LogManager.getLogger(AllPostServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet AllPostServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        List<PostDto> allPostsList = postService.getAllPosts();
        req.setAttribute("allPostsList", allPostsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/all_posts.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet AllPostServlet.");
    }
}
