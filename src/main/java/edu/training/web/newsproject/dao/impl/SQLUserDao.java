package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.AuthDao;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.RegDao;

import java.util.Optional;

public class SQLUserDao implements AuthDao, RegDao {
    @Override
    public void addUser(User user) {
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void saveUserRegInfo(UserRegInfo userRegInfo) throws DaoException {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public User checkToken(String token) {
        return null;
    }

    @Override
    public Optional<User> signIn(AuthInfo authInfo) {
        return Optional.empty();
    }
}
