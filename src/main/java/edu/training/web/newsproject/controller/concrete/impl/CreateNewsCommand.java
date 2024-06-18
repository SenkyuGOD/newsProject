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
import edu.training.web.newsproject.util.IDUtils;

public class CreateNewsCommand implements Command {
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String imageUrl = request.getParameter("imageUrl");

        News news = new News();
        news.setNewsTitle(title);
        news.setNewsContent(content);
        news.setNewsImg(imageUrl);
        news.setNewsId(IDUtils.generateID());


        try {
            newsService.createNews(news);
            response.sendRedirect("MyController?command=go_to_news_page&id=" + news.getNewsId());
        } catch (ServiceException e) {
            response.sendRedirect("MyController?command=go_to_index_page&authError=Something went wrong");
        }
    }
}
