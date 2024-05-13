package org.example.blog.servlets.user;

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
import org.example.blog.service.UserService;
import org.example.blog.service.impl.PostServiceImpl;
import org.example.blog.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final PostService postService = new PostServiceImpl();

    private static final Logger logger = LogManager.getLogger(WelcomeServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Вызвана функция doGet WelcomeServlet.");
        String name = String.valueOf(req.getSession().getAttribute("loggedUser"));
        UserDto user = userService.findByName(name);
        req.setAttribute("user", user);

        List<PostDto> postList = postService.findAllByUserId(user.getId());
        req.setAttribute("postList", postList);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/welcome.jsp");
        requestDispatcher.forward(req, resp);
        logger.info("Завершение работы функции doPost WelcomeServlet.");
    }


}
