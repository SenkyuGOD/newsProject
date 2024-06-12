package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.AuthDao;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.RegDao;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPool;
import edu.training.web.newsproject.dao.connetionpool.ConnectionPoolException;
import edu.training.web.newsproject.service.UserRoles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SQLUserDao implements AuthDao, RegDao {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

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

    private static final String INSERT_USER_SQL = "INSERT INTO user (username, email, password, Roles_id) VALUES (?, ?, ?, ?)";

    @Override
    public User saveUserRegInfo(UserRegInfo userRegInfo) throws DaoException {
       /* try (Connection connection = connectionPool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, userRegInfo.getUsername());
            statement.setString(2, userRegInfo.getEmail());
            statement.setString(3, userRegInfo.getPassword());
            statement.setInt(4, 4);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DaoException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    int id = generatedKeys.getInt(1);
                    return new User(id, userRegInfo.getUsername(),userRegInfo.getEmail(), UserRoles.READER);

                } else {
                    throw new DaoException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException | ConnectionPoolException e) {

            throw new DaoException("Error occurred during sign up", e);

        }*/

        return null;
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
