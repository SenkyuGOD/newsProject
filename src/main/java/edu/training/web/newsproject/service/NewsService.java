package edu.training.web.newsproject.service;

import edu.training.web.newsproject.beans.News;

import java.util.List;

public interface NewsService {
    void createNews(News news) throws ServiceException;
    void updateNews(News news) throws ServiceException;
    News getNewsById(Long id) throws ServiceException;
    News getNewsByTitle(String title) throws ServiceException;
    List<News> getAllNews() throws ServiceException;
}
