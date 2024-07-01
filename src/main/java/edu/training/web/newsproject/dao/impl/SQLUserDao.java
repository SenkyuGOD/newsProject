package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.UserDao;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPool;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPoolException;
import edu.training.web.newsproject.service.UserRoles;
import edu.training.web.newsproject.util.IDUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLUserDao implements UserDao {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private final Logger logger = Logger.getLogger(this.getClass().getName());

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

                    logger.log(Level.INFO, "User found: " + username);

                    UserRoles role;
                    try {
                        role = UserRoles.valueOf(roleStr.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new DaoException("Invalid role found for user.", e);
                    }

                    return new User(idUser, username, email, role);
                } else {
                    logger.log(Level.INFO, "User not found.");
                    throw new DaoException("Invalid username or password.");
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error occurred during sign in", e);
        }
    }


    private static final String CHECK_SUER_EXISTS_SQL = "SELECT COUNT(*) FROM user WHERE email = ?";

    @Override
    public boolean userExists(String email) throws DaoException {

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_SUER_EXISTS_SQL)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.log(Level.INFO, resultSet.getString("email"));
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        return true;
                    }
                }
            }

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during checking user existence", e);
            throw new DaoException("Error occurred during check existence", e);
        }
        return false;
    }

    private static final String INSERT_USER_SQL = "INSERT INTO user (idUser, username, email, password, token) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_USER_ROLE_SQL = "INSERT INTO user_has_role (user_idUser, role_idrole) VALUES (?, ?)";

    @Override
    public void signUp(UserRegInfo userRegInfo) throws DaoException {
        try (Connection connection = connectionPool.takeConnection()) {
            try (PreparedStatement userStmt = connection.prepareStatement(INSERT_USER_SQL);
                 PreparedStatement roleStmt = connection.prepareStatement(INSERT_USER_ROLE_SQL)) {
                logger.log(Level.INFO, "Inserting user into user_has_role");

                userStmt.setInt(1, userRegInfo.getUserId());
                userStmt.setString(2, userRegInfo.getUsername());
                userStmt.setString(3, userRegInfo.getEmail());
                userStmt.setString(4, userRegInfo.getPassword());
                userStmt.setString(5, userRegInfo.getToken());

                int affectedRows = userStmt.executeUpdate();

                if (affectedRows == 0) {
                    throw new DaoException("Creating user failed, no rows affected.");
                }

                roleStmt.setInt(1, userRegInfo.getUserId());
                roleStmt.setInt(2, userRegInfo.getRoles().ordinal()); // Преобразуем роль в int

                roleStmt.executeUpdate();

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error occurred during sign up", e);
                throw new DaoException("Error occurred during sign up", e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during sign up", e);
            throw new DaoException("Error occurred during sign up", e);
        }
    }


    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE idUser = ?;";

    @Override
    public void deleteUser(int id) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {

            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Deleting user failed, no rows affected.");
            }

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error occurred during deleting user", e);
        }
    }


    private static final String CHANGE_USER_ROLE_SQL = "UPDATE user_has_role SET role_idrole = ? WHERE user_idUser = ?;";

    @Override
    public void changeUserRole(int userId, int roleId) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_ROLE_SQL)) {
            logger.log(Level.INFO, "Changing user role to " + roleId);

            statement.setInt(1, roleId);
            statement.setInt(2, userId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                logger.log(Level.SEVERE, "Changing user role failed, no rows affected.");
                throw new DaoException("Changing user role failed, no rows affected.");
            }

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during changing user role", e);
            throw new DaoException("Error occurred during changing user role", e);
        }
    }


    private static final String GET_ROLE_ID = "SELECT idrole FROM role WHERE title = ?;";

    @Override
    public int getRoleId(String roleTitle) throws DaoException {
        try (Connection connection = connectionPool.takeConnection(); PreparedStatement statement = connection.prepareStatement(GET_ROLE_ID)) {
            logger.log(Level.INFO, "Getting role id for " + roleTitle);

            statement.setString(1, roleTitle);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return resultSet.getInt("idrole");
                } else {
                    logger.log(Level.SEVERE, "No role found for " + roleTitle);
                    throw new SQLException("Role not found: " + roleTitle);
                }
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.SEVERE, "Error occurred during getting role id for " + roleTitle, e);
            throw new DaoException("Error occurred during getting role id", e);
        }
    }

    private static final String CHANGE_USER_PASSWORD_SQL = "UPDATE user SET password = ? WHERE idUser = ?;";

    @Override
    public void changeUserPassword(int id, String newPassword) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_USER_PASSWORD_SQL)) {
            logger.log(Level.INFO, "Changing user password to " + newPassword);

            statement.setString(1, newPassword);
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                logger.log(Level.SEVERE, "Changing user password failed, no rows affected.");
                throw new DaoException("Changing user password failed, no rows affected.");
            }

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during changing user password", e);
            throw new DaoException("Error occurred during changing user password", e);
        }
    }

    private static final String UPDATE_USER_TOKEN_SQL = "UPDATE user SET token = ? WHERE idUser = ?";

    @Override
    public void updateUserToken(int userId, String token) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TOKEN_SQL)) {
            logger.log(Level.INFO, "Updating user token to " + token);

            statement.setString(1, token);
            statement.setInt(2, userId);

            statement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during updating user token", e);
            throw new DaoException("Error occurred during updating user token", e);
        }

    }

    private static final String FIND_USER_BY_TOKEN_SQL =
            "SELECT idUser, username, email, password, token FROM user WHERE token = ?";

    @Override
    public User findUserByToken(String token) throws DaoException {
        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_TOKEN_SQL)) {
            logger.log(Level.INFO, "Finding user by token " + token);

            statement.setString(1, token);

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
                    logger.log(Level.SEVERE, "No such user found for token " + token);
                    throw new DaoException("Invalid user token.");
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during finding user", e);
            throw new DaoException("Error occurred during finding user by token", e);
        }
    }


    private static final String SELECT_ALL_USERS_SQL = "SELECT u.idUser, u.username, u.email, r.title AS role" +
            "FROM user u" +
            "JOIN user_has_role ur ON u.idUser = ur.user_idUser" +
            "JOIN role r ON ur.role_idrole = r.idrole;";

    @Override
    public List<User> getAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();

        try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            logger.log(Level.INFO, "Getting all users from database");

            while (resultSet.next()) {
                int idUser = resultSet.getInt("idUser");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String roleName = resultSet.getString("role");
                UserRoles role = UserRoles.valueOf(roleName.toUpperCase());

                User user = new User(idUser, username, email, role);
                users.add(user);
            }

        } catch (SQLException | ConnectionPoolException e) {
            logger.log(Level.SEVERE, "Error occurred during getting all users", e);
            throw new DaoException("Error occurred during fetching all users", e);
        }

        return users;
    }

}
