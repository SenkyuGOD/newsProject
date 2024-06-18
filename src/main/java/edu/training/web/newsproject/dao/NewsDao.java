package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.beans.News;

import java.util.List;

public interface NewsDao {
    void createNews(News news) throws DaoException;

    void updateNews(News news) throws DaoException;

    News getNewsById(int id) throws DaoException;

    void deleteNewsById(int id) throws DaoException;

    News getNewsByTitle(String title) throws DaoException;

    List<News> getAllNews() throws DaoException;
}
