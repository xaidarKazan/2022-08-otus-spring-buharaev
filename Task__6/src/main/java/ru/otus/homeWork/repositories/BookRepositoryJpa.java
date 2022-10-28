package ru.otus.homeWork.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homeWork.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long getBooksCount() {
        TypedQuery<Long> query = em.createQuery("select count(b.id) from Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == null) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Book book = getById(id);
        if(book != null)
            em.remove(book);
    }
}