package edu.training.web.newsproject.dao;

import edu.training.web.newsproject.beans.Comment;

import java.util.List;

public interface CommentDao {
    Comment createComment(Comment comment) throws DaoException;

    Comment updateComment(Comment comment) throws DaoException;

    void deleteComment(Long id) throws DaoException;

    List<Comment> getCommentsByNewsId(Long newsId) throws DaoException;
}
