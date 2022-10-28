package ru.otus.homeWork.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@AllArgsConstructor
public class CommentRepositoryJpa implements CommentRepository{

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() == null) {
            em.persist(comment);
            return comment;
        }
        else return em.merge(comment);
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void deleteById(long id) {
        Comment comment = getById(id);
        if(comment != null)
            em.remove(comment);
    }
}