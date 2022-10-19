package ru.otus.homeWork.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {

    private final EntityManager em;

    @Override
    public long getAuthorsCount() {
        return getAll().size();
    }

    @Override
    public Author save(Author author) {
        if(author.getId() == null) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Author author = em.find(Author.class, id);
        if(author != null)
            em.remove(author);
    }
}