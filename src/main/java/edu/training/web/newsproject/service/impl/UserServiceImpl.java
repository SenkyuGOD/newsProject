package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.AuthInfo;
import edu.training.web.newsproject.beans.User;
import edu.training.web.newsproject.beans.UserRegInfo;
import edu.training.web.newsproject.dao.AuthDao;
import edu.training.web.newsproject.dao.DaoException;
import edu.training.web.newsproject.dao.DaoProvider;
import edu.training.web.newsproject.dao.RegDao;
import edu.training.web.newsproject.service.ServiceException;
import edu.training.web.newsproject.service.UserService;

import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final AuthDao authDao = DaoProvider.getInstance().getAuthDao();
    private final RegDao regDao = DaoProvider.getInstance().getRegDao();


    @Override
    public Optional<User> signIn(AuthInfo authInfo) throws ServiceException {

        return authDao.signIn(authInfo);
    }

    @Override
    public User rememberMe(String token) throws ServiceException {
        return authDao.checkToken(token);
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        Optional<User> existingUser = regDao.getUserByEmail(user.getEmail());
        if (existingUser.isPresent() && !Objects.equals(existingUser.get().getUserId(), user.getUserId())) {
            throw new ServiceException("Another user with this email already exists");
        }
        return regDao.updateUser(user);
    }

    @Override
    public void registration(User user, UserRegInfo regInfo) throws ServiceException {
        try {
            Optional<User> existingUser = regDao.getUserByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                throw new ServiceException("User with this email already exists");
            }

            regDao.addUser(user);
            regDao.saveUserRegInfo(regInfo);
        } catch (DaoException e) {
            throw new ServiceException("Error registering user", e);
        }
    }

    @Override
    public Optional<User> getInfo(AuthInfo authInfo) throws ServiceException {
        Optional<User> result = authDao.getUserByUsername(authInfo.getUsername());
        return result;
    }


}
