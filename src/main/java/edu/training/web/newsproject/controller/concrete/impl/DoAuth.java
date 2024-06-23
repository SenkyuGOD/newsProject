package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import edu.training.web.newsproject.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

public class DoAuth implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Perform user authentication and authorization. Login: " + login);
        User user;
        try {
            user = userService.signIn(new AuthInfo(login, password));

            if(user != null) {
                HttpSession session = (HttpSession) request.getSession(true);
                session.setAttribute("user", user);

                String rememberMe = request.getParameter("remember-me");
                if (rememberMe != null) {
                    Cookie cookie = new Cookie("remember-me", "user-token123");
                    cookie.setHttpOnly(true);
                    cookie.setSecure(true);
                    response.addCookie(cookie);
                }

                response.sendRedirect("MyController?command=go_to_index_page");

            }else {
                response.sendRedirect("MyController?command=go_to_index_page&authError=Wrong login or password!");
            }

        } catch (ServiceException e) {
            // logging
            response.sendRedirect("MyController?command=go_to_auth_page&authError=Wrong login or password!");
        }
    }
}
