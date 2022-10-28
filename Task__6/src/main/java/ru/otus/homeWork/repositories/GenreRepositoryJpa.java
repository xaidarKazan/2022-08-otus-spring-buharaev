package ru.otus.homeWork.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long getGenresCount() {
        TypedQuery<Long> query = em.createQuery("select count(g.id) from Genre g ", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Genre save(Genre genre) {
        if(genre.getId() == null) {
            em.persist(genre);
            return genre;
        }
        else return em.merge(genre);
    }

    @Override
    public Genre getById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Genre genre = getById(id);
        if(genre != null)
            em.remove(genre);
    }
}