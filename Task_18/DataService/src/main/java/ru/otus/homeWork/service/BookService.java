package ru.otus.homeWork.service;

import ru.otus.homeWork.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book save(Book book);

    void delete(long id);
}