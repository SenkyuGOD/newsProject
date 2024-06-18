package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.DaoProvider;
import edu.training.web.newsproject.dao.NewsDao;
import edu.training.web.newsproject.service.NewsService;
import edu.training.web.newsproject.service.ServiceException;

import java.util.List;
import java.util.Objects;

public class NewsServiceImpl implements NewsService {
    private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();

    @Override
    public void createNews(News news) throws ServiceException {
        try {
            newsDao.createNews(news);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNews(News news) throws ServiceException {
        try {
            newsDao.updateNews(news);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public News getNewsById(Long id) throws ServiceException {
        try {
            List<News> newsList = newsDao.getAllNews();
            News news1 = null;
            for (News news : newsList) {
                if (Objects.equals(news.getNewsId(), id)) {
                    news1 = news;
                }
            }
            return news1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public News getNewsByTitle(String title) throws ServiceException {
        try {
            List<News> newsList = newsDao.getAllNews();
            News news1 = null;
            for (News news : newsList) {
                if (Objects.equals(news.getNewsTitle(), title)) {
                    news1 = news;
                }
            }
            return news1;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getAllNews() throws ServiceException {
        try {
            return newsDao.getAllNews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
