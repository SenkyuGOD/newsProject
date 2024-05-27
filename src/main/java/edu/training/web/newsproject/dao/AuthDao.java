package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;

import java.util.Optional;

public interface AuthDao {

    Optional<User> getUserByUsername(String username);

    boolean userExists(String username);

    User checkToken(String token);

    Optional<User> signIn(AuthInfo authInfo);

}
