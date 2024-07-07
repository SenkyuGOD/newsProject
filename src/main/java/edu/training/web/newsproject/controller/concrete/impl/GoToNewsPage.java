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
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoToNewsPage implements Command {
    private static final Logger logger = Logger.getLogger(GoToNewsPage.class.getName());
    private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            logger.info("Received id parameter: {}");

            int id = Integer.parseInt(idParam);
            News mainNews = newsService.getNewsById(id);
            logger.info("Retrieved news: {}");

            if (mainNews == null) {
                logger.warning("No news found for id: {}");
                response.sendRedirect("MyController?command=go_to_index_page&authError=News not found");
                return;
            }

            request.setAttribute("news", mainNews);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/newsPage.jsp");
            rd.forward(request, response);
        } catch (NumberFormatException e) {
            logger.log(Level.parse("Invalid id format: {}"), request.getParameter("id"), e);
            response.sendRedirect("MyController?command=go_to_index_page&authError=Invalid id format");
        } catch (ServiceException e) {
            logger.info("asdfasdfasdf");
            response.sendRedirect("MyController?command=go_to_index_page&authError=Something went wrong");
        }
    }
}
