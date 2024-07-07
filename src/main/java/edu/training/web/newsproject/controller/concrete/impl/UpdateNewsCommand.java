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
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateNewsCommand implements Command {
    private static final Logger logger = Logger.getLogger(UpdateNewsCommand.class.getName());
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            logger.log(Level.WARNING, "Missing ID parameter");
            response.sendRedirect("MyController?command=go_to_index_page&authError=Invalid ID");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            logger.log(Level.WARNING, "Invalid ID format: " + idStr);
            response.sendRedirect("MyController?command=go_to_index_page&authError=Invalid ID format");
            return;
        }

        String title = request.getParameter("newsTitle");
        String content = request.getParameter("newsContent");
        String imgPath = request.getParameter("imgPath");

        if (title == null || content == null || imgPath == null) {
            logger.log(Level.WARNING, "Missing parameters for updating news");
            response.sendRedirect("MyController?command=go_to_index_page&authError=Missing parameters");
            return;
        }

        try {
            News news = newsService.getNewsById(id);

            news.setNewsTitle(title);
            news.setNewsContent(content);
            news.setNewsImg(imgPath);

            newsService.updateNews(news);

            logger.log(Level.INFO, "News updated successfully with ID: " + id);
            response.sendRedirect("MyController?command=go_to_news_page&id=" + id);
        } catch (ServiceException e) {
            logger.log(Level.SEVERE, "Failed to update news", e);
            response.sendRedirect("MyController?command=go_to_index_page&authError=Something went wrong");
        }
    }
}
