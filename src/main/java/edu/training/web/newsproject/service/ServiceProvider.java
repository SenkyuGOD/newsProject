package edu.training.web.newsproject.service;

import edu.training.web.newsproject.service.impl.NewsServiceImpl;
import edu.training.web.newsproject.service.impl.UserServiceImpl;
import lombok.Getter;

public class ServiceProvider {
    @Getter
    private static final ServiceProvider instance = new ServiceProvider();

    @Getter
    private final UserService userService = new UserServiceImpl();
    @Getter
    private final NewsService newsService = new NewsServiceImpl();



    private ServiceProvider() {
    }

}
