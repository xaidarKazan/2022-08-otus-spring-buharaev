package ru.otus.homeWork.service;

import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    void delete(long id);
}