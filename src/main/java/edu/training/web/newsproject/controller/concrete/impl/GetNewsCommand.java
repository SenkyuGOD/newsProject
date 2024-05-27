package edu.training.web.newsproject.controller.concrete.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.controller.concrete.Command;
import edu.training.web.newsproject.service.NewsService;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GetNewsCommand implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String title = request.getParameter("title");

            try{
                News news = newsService.getNewsByTitle(title);
                response.sendRedirect("MyController?command=go_to_news_page&id=" + news.getNewsId());
            } catch (ServiceException e) {
                response.sendRedirect("MyController?command=go_to_index_page&authError=Something went wrong");
            }
    }
}
