package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;

import java.util.Optional;

public interface RegDao {

    void addUser(User user);

    boolean updateUser(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    User saveUserRegInfo(UserRegInfo userRegInfo) throws DaoException;
}
