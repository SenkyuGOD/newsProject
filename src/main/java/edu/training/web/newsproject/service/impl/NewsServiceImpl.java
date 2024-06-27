package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.DaoProvider;
import edu.training.web.newsproject.dao.NewsDao;
import edu.training.web.newsproject.service.NewsService;
import edu.training.web.newsproject.service.ServiceException;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsServiceImpl implements NewsService {
    private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();
    private final Logger logger = Logger.getLogger(NewsServiceImpl.class.getName());

    @Override
    public void createNews(News news) throws ServiceException {
        try {
            logger.log(Level.INFO, "create news");
            newsDao.createNews(news);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "createNews error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateNews(News news) throws ServiceException {
        try {
            logger.log(Level.INFO, "update news");
            newsDao.updateNews(news);
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "updateNews error", e);
            throw new ServiceException(e);
        }
    }


    @Override
    public News getNewsById(Long id) throws ServiceException {
        try {
            logger.log(Level.INFO, "getNewsById");
            List<News> newsList = newsDao.getAllNews();
            News news1 = null;
            for (News news : newsList) {
                if (Objects.equals(news.getNewsId(), id)) {
                    news1 = news;
                }
            }
            return news1;
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "getNewsById error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public News getNewsByTitle(String title) throws ServiceException {
        try {
            logger.log(Level.INFO, "getNewsByTitle");
            List<News> newsList = newsDao.getAllNews();
            News news1 = null;
            for (News news : newsList) {
                if (Objects.equals(news.getNewsTitle(), title)) {
                    news1 = news;
                }
            }
            return news1;
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "getNewsByTitle error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<News> getAllNews() throws ServiceException {
        try {
            logger.log(Level.INFO, "getAllNews");
            return newsDao.getAllNews();
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "getAllNews error", e);
            throw new ServiceException(e);
        }
    }
}
