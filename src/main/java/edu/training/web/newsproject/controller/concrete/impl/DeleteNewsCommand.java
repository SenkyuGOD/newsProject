package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.NewsService;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteNewsCommand implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        int newsId = Integer.parseInt(id);
        try {
            newsService.deleteNewsById(newsId);
            RequestDispatcher rd = request.getRequestDispatcher("main_index.jsp");
            rd.forward(request, response);
        } catch (ServiceException e) {
            request.getRequestDispatcher("WEB-INF/views/main_index&DeleteError.jsp").forward(request, response);
        }
    }
}
