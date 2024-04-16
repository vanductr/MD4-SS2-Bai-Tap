package rikkei.academy.service;

import rikkei.academy.model.entity.Comment;

import java.util.List;

public interface ICommentService extends IGenericService<Comment>{
    public List<Comment> findTodayComment();
}
