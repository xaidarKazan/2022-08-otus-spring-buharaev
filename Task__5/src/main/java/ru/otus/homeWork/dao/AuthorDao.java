package ru.otus.homeWork.dao;

import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorDao {

    long getAuthorsCount();

    void insert(Author author);

    Author getById(long id);

    List<Author> getAll();

    void deleteById(long id);
}