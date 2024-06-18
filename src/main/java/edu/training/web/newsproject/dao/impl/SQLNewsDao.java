package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.News;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.NewsDao;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPool;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPoolException;
import edu.training.web.newsproject.util.IDUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLNewsDao implements NewsDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final Logger logger = Logger.getLogger(SQLNewsDao.class.getName());

    public SQLNewsDao() {
    }

    private static final String INSERT_NEWS_SQL = "INSERT INTO news (idnews, title, brief, img_path) VALUES (?, ?, ?, ?)";

    @Override
    public void createNews(News news) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEWS_SQL)) {

            logger.log(Level.INFO, "Creating new news " + news);

            int newsId = IDUtils.generateID();
            statement.setInt(1, newsId);
            statement.setString(2, news.getNewsTitle());
            statement.setString(3, news.getNewsContent());
            statement.setString(4, news.getNewsImg());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("Failed to create new news " + news);
            }

            logger.log(Level.INFO, "News created with ID " + newsId);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Failed to create new news " + news, e);
        }
    }

    private static final String UPDATE_NEWS_SQL = "UPDATE news SET title = ?, brief = ?, img_path = ? WHERE idnews = ?";

    @Override
    public void updateNews(News news) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_SQL)) {

            logger.log(Level.INFO, "Updating new news " + news);

            statement.setString(1, news.getNewsTitle());
            statement.setString(2, news.getNewsContent());
            statement.setString(3, news.getNewsImg());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("Failed to update new news " + news);
            }

            logger.log(Level.INFO, "News updated with ID " + news.getNewsId());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Failed to update new news " + news, e);
        }
    }

    private static final String GET_NEWS_SQL = "SELECT * FROM news WHERE idnews = ?";

    @Override
    public News getNewsById(int id) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NEWS_SQL)) {

            logger.log(Level.INFO, "Getting new news " + id);

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int newsId = id;
                    String title = resultSet.getString("title");
                    String brief = resultSet.getString("brief");
                    String img_path = resultSet.getString("img_path");

                    return new News(newsId, title, brief, img_path);

                } else {
                    throw new DaoException("Failed to get new news " + id);
                }

            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Failed to get new news " + id, e);
        }
    }

    private static final String DELETE_NEWS_SQL = "DELETE FROM news WHERE idnews = ?";

    @Override
    public void deleteNewsById(int id) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NEWS_SQL)) {

            logger.log(Level.INFO, "Deleting new news " + id);

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new DaoException("Failed to delete new news " + id);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Failed to delete new news " + id, e);
        }

    }

    private static final String GET_NEWS_SQL_BY_TITLE = "SELECT * FROM news WHERE title = ?";

    @Override
    public News getNewsByTitle(String title) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_NEWS_SQL_BY_TITLE)) {
            logger.log(Level.INFO, "Getting new news " + title);

            statement.setString(1, title);

            try (ResultSet resultSet = statement.executeQuery()) {
                int newsID = resultSet.getInt("idnews");
                String titleNews = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String img = resultSet.getString("img_path");

                return new News(newsID, titleNews, brief, img);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Failed to get new news " + title, e);
        }
    }

    private static final String GET_ALL_NEWS_SQL = "SELECT idnews, title, brief, img_path FROM news";

    @Override
    public List<News> getAllNews() throws DaoException {
        List<News> newsList = new ArrayList<>();
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_NEWS_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            logger.log(Level.INFO, "Getting all news");

            while (resultSet.next()) {
                int idnews = resultSet.getInt("idnews");
                String title = resultSet.getString("title");
                String brief = resultSet.getString("brief");
                String imgPath = resultSet.getString("img_path");

                News news = new News(idnews, title, brief, imgPath);
                newsList.add(news);
            }


        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.INFO, "Failed to get all news", e);
            throw new DaoException("Failed to get all news", e);
        }

        logger.log(Level.INFO, "All news list size is " + newsList.size());
        return newsList;
    }
}
