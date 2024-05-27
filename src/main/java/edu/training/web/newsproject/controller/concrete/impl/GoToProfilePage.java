package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.ServiceProvider;
import edu.training.web.newsproject.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToProfilePage implements Command {
    private final UserService UserService = ServiceProvider.getInstance().getUserService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/profilePage.jsp");
        dispatcher.forward(request, response);
    }
}
