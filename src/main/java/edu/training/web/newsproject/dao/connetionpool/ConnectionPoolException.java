package edu.training.web.newsproject.dao.connetionpool;

import java.io.Serial;

public class ConnectionPoolException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {super(message, e);}
}
