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
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/update_post")
public class UpdatePostServlet extends HttpServlet {
    private final UserServiceImpl userService = new UserServiceImpl();
    private final PostServiceImpl postService = new PostServiceImpl();

    private static final Logger logger = LogManager.getLogger(UpdatePostServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet UpdatePostServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        Long id = Long.valueOf(req.getParameter("id"));
        PostDto post = postService.findPostById(id);

        req.setAttribute("id", id);
        req.setAttribute("header", post.getHeader());
        req.setAttribute("content", post.getContent());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/update_post.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doGet UpdatePostServlet.");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doPost UpdatePostServlet.");
        try {
            String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
            UserDto user = userService.findByName(name);
            req.setAttribute("user", user);

            Long id = Long.valueOf(req.getParameter("id"));
            String header = req.getParameter("header");
            String content = req.getParameter("content");

            boolean updated = postService.updatePost(id, header, content);
            req.setAttribute("postUpdated", updated);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/post/update_post.jsp");
            requestDispatcher.forward(req, resp);
            logger.info("Пост успешно обновлен.");
        } catch (Exception e ){
            req.setAttribute("error", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/error.jsp");
            requestDispatcher.forward(req, resp);
            logger.error("Ошибка при обновлении поста.");
        }
        logger.info("Завершение работы функции doPost UpdatePostServlet.");
    }
}
