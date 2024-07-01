package edu.training.web.newsproject.service;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;

import java.util.List;

public interface UserService {
    User signIn(AuthInfo authInfo) throws ServiceException;

    void signUp(UserRegInfo regInfo) throws ServiceException;

    User rememberMe(String token) throws ServiceException;

    String createRememberMeToken(int userId) throws ServiceException;

    void changePassword(int id, String newPassword) throws ServiceException;

    void deleteUser(int id) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;
}
