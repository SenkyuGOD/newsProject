package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;

import java.util.Map;

public interface UserDao {
    User signIn (AuthInfo authInfo) throws DaoException;

    boolean userExists(String email) throws DaoException;

    User signUp(UserRegInfo userRegInfo) throws DaoException;

    void deleteUser(int id) throws DaoException;

    void changeUserRole(int userId, int roleId) throws DaoException;

    int getRoleId(String roleName) throws DaoException;

    void changeUserPassword(int id, String newPassword) throws DaoException;

    Map<String, User> getAllUsers() throws DaoException;
}
