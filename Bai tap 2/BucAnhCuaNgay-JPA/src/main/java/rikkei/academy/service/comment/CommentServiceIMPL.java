package rikkei.academy.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.model.entity.Comment;
import rikkei.academy.repository.ICommentRepository;
import rikkei.academy.service.ICommentService;

import java.util.List;

@Service
public class CommentServiceIMPL implements ICommentService {
    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.remove(id);
    }

    @Override
    public List<Comment> findTodayComment() {
        return commentRepository.findTodayComment();
    }
}
