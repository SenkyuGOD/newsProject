package edu.training.web.newsproject.service;

import edu.training.web.newsproject.service.impl.CommentServiceImpl;
import edu.training.web.newsproject.service.impl.NewsServiceImpl;
import edu.training.web.newsproject.service.impl.UserServiceImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();
    private final CommentService commentService = new CommentServiceImpl();


    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public NewsService getNewsService() {
        return newsService;
    }

    public CommentService getCommentService() {
        return commentService;
    }
}
