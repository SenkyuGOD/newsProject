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

public class GoToIndexPage implements Command {
   private final NewsService infoService = ServiceProvider.getInstance().getNewsService();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



            try{
            List<News> subNews = infoService.getAllNews();
            request.setAttribute("AllNews", subNews);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main_index.jsp");
            dispatcher.forward(request, response);}
            catch (ServiceException e){
                e.printStackTrace();
            }
    }
}
