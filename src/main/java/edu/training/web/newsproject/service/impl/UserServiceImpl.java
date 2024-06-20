package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.DaoProvider;
import edu.training.web.newsproject.dao.UserDao;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.UserService;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoProvider.getInstance().getUserDao();

    public static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());


    @Override
    public User signIn(AuthInfo authInfo) throws ServiceException {

        log.log(Level.INFO, "signIn");

        User user;

        try {
            user = userDao.signIn(authInfo);
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void signUp(UserRegInfo regInfo) throws ServiceException {

        log.log(Level.INFO, "signUp");


        try {
            userDao.signUp(regInfo);
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public User rememberMe(String token) throws ServiceException {
        try {
            log.log(Level.INFO, "rememberMe");
            return userDao.findUserByToken(token);
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public String createRememberMeToken(int userId) throws ServiceException {
        try {
            log.log(Level.INFO, "createRememberMeToken");
            String token = UUID.randomUUID().toString();

            userDao.updateUserToken(userId, token);
            return token;
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteUser(int id) throws ServiceException {
        try {
            log.log(Level.INFO, "deleteUser");
            userDao.deleteUser(id);
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }

    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            log.log(Level.INFO, "getAllUsers");
            return userDao.getAllUsers();
        } catch (DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
    }
}
