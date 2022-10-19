package ru.otus.homeWork.repositories;

import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorRepository {

    long getAuthorsCount();

    Author save(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}