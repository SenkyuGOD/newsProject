package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import edu.training.web.newsproject.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ChangePassword implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (currentPassword.equals(newPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/jsp/profilePage.jsp").forward(request, response);
        }

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/jsp/profilePage.jsp").forward(request, response);
        }

        try {
            userService.changePassword(user.getUserId(), newPassword);
        } catch (ServiceException e) {
            session.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/profilePage.jsp").forward(request, response);
        }
    }
}
