package edu.training.web.newsproject.service.impl;

import edu.training.web.newsproject.beans.Comment;
import edu.training.web.newsproject.service.CommentService;
import edu.training.web.newsproject.service.ServiceException;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public Comment addComment(Comment comment) throws ServiceException {
        return null;
    }

    @Override
    public Comment updateComment(Comment comment) throws ServiceException {
        return null;
    }

    @Override
    public void deleteComment(Long id) throws ServiceException {

    }

    @Override
    public List<Comment> getCommentsByNewsId(Long newsId) throws ServiceException {
        return List.of();
    }
}
