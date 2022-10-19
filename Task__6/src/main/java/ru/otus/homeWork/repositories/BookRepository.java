package ru.otus.homeWork.repositories;

import ru.otus.homeWork.domain.Book;

import java.util.List;

public interface BookRepository {

    long getBooksCount();

    Book save(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}