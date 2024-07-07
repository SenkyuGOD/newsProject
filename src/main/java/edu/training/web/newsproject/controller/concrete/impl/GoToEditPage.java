package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.NewsService;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditPage implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        int newsId = Integer.parseInt(id);

        try {
            News news = newsService.getNewsById(newsId);
            request.setAttribute("news", news);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editNewsPage.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceException e) {
            response.sendRedirect("MyController?command=go_to_index_page&authError=Invalid ID");
        }

    }
}
