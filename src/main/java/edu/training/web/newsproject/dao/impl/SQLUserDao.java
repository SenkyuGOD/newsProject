package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.UserDao;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPool;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPoolException;
import edu.training.web.newsproject.service.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class SQLUserDao implements UserDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String SIGN_IN_SQL =
            "SELECT u.idUser, u.username, u.email, u.password, r.title AS role " +
                    "FROM user u " +
                    "JOIN user_has_role ur ON u.idUser = ur.user_idUser " +
                    "JOIN role r ON ur.role_idrole = r.idrole " +
                    "WHERE u.username = ? AND u.password = ?;";

    @Override
    public User signIn(AuthInfo authInfo) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SIGN_IN_SQL)) {

            statement.setString(1, authInfo.getUsername());
            statement.setString(2, authInfo.getPassword());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idUser = resultSet.getInt("idUser");
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String roleStr = resultSet.getString("role");

                    UserRoles role;
                    try {
                        role = UserRoles.valueOf(roleStr.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new DaoException("Invalid role found for user.", e);
                    }

                    return new User(idUser, username, email, role);
                } else {
                    throw new DaoException("Invalid username or password.");
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error occurred during sign in", e);
        }
    }



    @Override
    public boolean userExists(String email) throws DaoException {
        return false;
    }

    private static final String INSERT_USER_SQL = "INSERT INTO user (idUser, username, email, password) VALUES (?, ?, ?, ?)";

    @Override
    public User signUp(UserRegInfo userRegInfo) throws DaoException {
        try (Connection connection = connectionPool.takeConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, 1); // You can replace 1 with a logic to generate or fetch the id
            statement.setString(2, userRegInfo.getUsername());
            statement.setString(3, userRegInfo.getEmail());
            statement.setString(4, userRegInfo.getPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    return new User(id, userRegInfo.getUsername(), userRegInfo.getEmail(), UserRoles.READER);
                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error occurred during sign up", e);
        }
    }

    @Override
    public void deleteUser(int id) throws DaoException {

    }

    @Override
    public void changeUserRole(int userId, int roleId) throws DaoException {

    }

    private static final String GET_ROLE_ID = "SELECT idrole FROM role WHERE title = ?;";

    @Override
    public int getRoleId(String roleTitle) throws DaoException {
        try (Connection connection = connectionPool.takeConnection(); PreparedStatement statement = connection.prepareStatement(GET_ROLE_ID)) {

            statement.setString(1, roleTitle);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("idrole");
                } else {
                    throw new SQLException("Role not found: " + roleTitle);
                }
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException("Error occurred during getting role id", e);
        }
    }

    @Override
    public void changeUserPassword(int id, String newPassword) throws DaoException {

    }

    @Override
    public Map<String, User> getAllUsers() throws DaoException {
        return Map.of();
    }
}
