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
import java.util.List;
import java.util.Optional;

public class GoToNewsPage implements Command {
   private final NewsService infoService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        News mainNews = null;
        try {
            long id = Integer.parseInt(request.getParameter("id"));
            mainNews = infoService.getNewsById(id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        request.setAttribute("news", mainNews);
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/newsPage.jsp");
        rd.forward(request, response);
    }
}
