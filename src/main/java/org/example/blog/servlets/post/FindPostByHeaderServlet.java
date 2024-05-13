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

import java.io.IOException;
import java.util.List;

@WebServlet("/find_post")
public class FindPostByHeaderServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostService postService = new PostServiceImpl();

    private static final Logger logger = LogManager.getLogger(FindPostByHeaderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet FindPostByHeaderServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        String header = req.getParameter("header");
        List<PostDto> postsList = postService.findByHeader(header);
        req.setAttribute("postsList", postsList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/find_post.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet FindPostByHeaderServlet.");
    }
}
