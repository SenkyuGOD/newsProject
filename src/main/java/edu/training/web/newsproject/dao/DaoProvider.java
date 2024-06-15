package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.dao.impl.SQLNewsDao;
import edu.training.web.newsproject.dao.impl.SQLUserDao;
import lombok.Getter;

@Getter
public class DaoProvider {
    private static final DaoProvider INSTANCE = new DaoProvider();

    private final UserDao userDao = new SQLUserDao();

    private final NewsDao newsDao = new SQLNewsDao();


    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return INSTANCE;
    }
}
