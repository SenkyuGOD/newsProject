package edu.training.web.newsproject.dao.impl;

import edu.training.web.newsproject.beans.Comment;
import edu.training.web.newsproject.dao.CommentDao;
import edu.training.web.newsproject.dao.DaoException;

import java.util.List;

public class SQLCommentDao implements CommentDao {
    @Override
    public Comment createComment(Comment comment) throws DaoException {
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) throws DaoException {
        return null;
    }

    @Override
    public void deleteComment(Long id) throws DaoException {

    }

    @Override
    public List<Comment> getCommentsByNewsId(Long newsId) throws DaoException {
        return List.of();
    }
}
