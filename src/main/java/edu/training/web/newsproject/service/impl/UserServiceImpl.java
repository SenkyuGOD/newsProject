package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.DaoProvider;
import edu.training.web.newsproject.dao.UserDao;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.UserService;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = DaoProvider.getInstance().getUserDao();

    public static final  Logger log = Logger.getLogger(UserServiceImpl.class.getName());


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
    public User signUp(UserRegInfo regInfo) throws ServiceException {

        log.log(Level.INFO, "signUp");
        User user;

        try {
            user = userDao.signUp(regInfo);
        }catch(DaoException e) {
            log.log(Level.INFO, "DaoException", e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public void changeUserPassword(int id, String newPassword, String confirmPassword) throws ServiceException {

    }

    @Override
    public User rememberMe(String token) throws ServiceException {
        return null;
    }

    @Override
    public void deleteUser(int id) throws ServiceException {

    }

    @Override
    public Map<String, User> getAllUsers() throws ServiceException {
        return Map.of();
    }
}
