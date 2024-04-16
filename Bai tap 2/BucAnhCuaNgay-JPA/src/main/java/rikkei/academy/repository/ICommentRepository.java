package rikkei.academy.repository;

import rikkei.academy.model.entity.Comment;

import java.util.List;

public interface ICommentRepository extends IGeneralRepository<Comment>{
    public List<Comment> findTodayComment();
}
