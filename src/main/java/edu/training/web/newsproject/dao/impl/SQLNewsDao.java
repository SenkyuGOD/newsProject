package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.NewsDao;

import java.util.List;

public class SQLNewsDao implements NewsDao {
    @Override
    public void createNews(News news) throws DaoException {
    }

    @Override
    public void updateNews(News news) throws DaoException {
    }


    @Override
    public News getNewsById(Long id) throws DaoException {
        return null;
    }

    @Override
    public News getNewsByTitle(String title) throws DaoException {
      return null;
    }

    @Override
    public List<News> getAllNews() throws DaoException {
        return List.of();
    }
}
