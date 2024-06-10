package edu.training.web.newsproject.dao.connetionpool;

import lombok.Getter;

import java.util.ResourceBundle;

public class DBResourceManager {
    @Getter
    private final static DBResourceManager instance = new DBResourceManager();

    ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");

    public String getValue(String key) {
        return jdbcProperties.getString(key);
    }
}
