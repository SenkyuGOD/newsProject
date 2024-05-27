package edu.training.web.newsproject.service;

import edu.training.web.newsproject.beans.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment) throws ServiceException;
    Comment updateComment(Comment comment) throws ServiceException;
    void deleteComment(Long id) throws ServiceException;
    List<Comment> getCommentsByNewsId(Long newsId) throws ServiceException;
}
