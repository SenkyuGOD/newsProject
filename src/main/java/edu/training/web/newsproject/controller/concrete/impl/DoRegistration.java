package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import edu.training.web.newsproject.service.UserRoles;
import edu.training.web.newsproject.service.UserService;
import edu.training.web.newsproject.util.IDUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoRegistration implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String confirmPassword = request.getParameter("confirm-password");
        String role = request.getParameter("role");

        try {
            if (username == null || username.isEmpty() ||
                    password == null || password.isEmpty() ||
                    email == null || email.isEmpty() ||
                    confirmPassword == null || confirmPassword.isEmpty() ||
                    role == null || role.isEmpty()) {

                request.setAttribute("error", "Please fill all the fields");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "Passwords do not match");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp");
                dispatcher.forward(request, response);
                return;
            }


            UserRegInfo userRegInfo = new UserRegInfo();
            userRegInfo.setUserId(IDUtils.generateID());
            userRegInfo.setUsername(username);
            userRegInfo.setPassword(password);
            userRegInfo.setEmail(email);
            userRegInfo.setConfirmPassword(confirmPassword);


            UserRoles userRole = UserRoles.valueOf(role.toUpperCase());
            userRegInfo.setRoles(userRole);


            userService.signUp(userRegInfo);


            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp");
            dispatcher.forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid role selected");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registrationPage.jsp");
            dispatcher.forward(request, response);
        }
    }

}
