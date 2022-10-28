package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface BookUIService {

    Long getId();
    Book save();
    boolean update(Book book);

    void isSaved(boolean isSaved);
    void isExists(Author author, Long id);

    void isExists(Genre genre, Long id);

    void print(Book book);

    void print(List<Book> books);

    void print(long count);
}