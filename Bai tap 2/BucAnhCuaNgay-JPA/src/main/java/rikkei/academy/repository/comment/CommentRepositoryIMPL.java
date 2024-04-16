package rikkei.academy.repository.comment;

import org.springframework.transaction.annotation.Transactional;
import rikkei.academy.model.entity.Comment;
import rikkei.academy.repository.ICommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Transactional
public class CommentRepositoryIMPL implements ICommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment findById(Long id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where  c.id=:id", Comment.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() != null) {
            em.merge(comment);
        } else {
            em.persist(comment);
        }
    }

    @Override
    public void remove(Long id) {
        Comment comment = findById(id);
        if (comment != null) {
            em.remove(comment);
        }
    }

    public List<Comment> findTodayComment() {
        LocalDate today = LocalDate.now();
        Date todayDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        TypedQuery<Comment> query = em.createQuery(
                "SELECT c FROM Comment c WHERE DATE(c.createdDate) = :today", Comment.class);
        query.setParameter("today", todayDate);
        return query.getResultList();
    }

}
