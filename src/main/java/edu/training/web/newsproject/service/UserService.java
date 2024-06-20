package edu.training.web.newsproject.service;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;

import java.util.Map;

public interface UserService {
    User signIn(AuthInfo authInfo) throws ServiceException;

    void signUp(UserRegInfo regInfo) throws ServiceException;

    void changeUserPassword(int id, String newPassword, String confirmPassword) throws ServiceException;

    User rememberMe(String token) throws ServiceException;


    void deleteUser(int id) throws ServiceException;

    Map<String, User> getAllUsers() throws ServiceException;
}
