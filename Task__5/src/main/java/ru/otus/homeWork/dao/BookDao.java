package ru.otus.homeWork.dao;

import ru.otus.homeWork.domain.Book;

import java.util.List;

public interface BookDao {

    long getBooksCount();

    void insert(Book book);

    Book getById(long id);

    List<Book> getAll();

    void deleteById(long id);
}