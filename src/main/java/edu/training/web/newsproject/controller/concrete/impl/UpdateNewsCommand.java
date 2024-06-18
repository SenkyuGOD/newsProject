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

public class UpdateNewsCommand implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Integer.parseInt(request.getParameter("id"));
        try {
            News news = new News();
            News oldNews = newsService.getNewsById(id);

            news.setNewsTitle(oldNews.getNewsTitle());
            news.setNewsContent(oldNews.getNewsContent());
            news.setNewsImg(request.getParameter("img"));

            newsService.updateNews(news);
        } catch (ServiceException e) {
            response.sendRedirect("MyController?command=go_to_index_page&authError=Something went wrong");
        }
    }
}
