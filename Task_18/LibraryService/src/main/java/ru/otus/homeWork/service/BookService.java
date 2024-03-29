package ru.otus.homeWork.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.otus.homeWork.domain.Book;

import java.util.List;

public interface BookService {
    ResponseEntity<List<Book>> findAll();

    ResponseEntity<Book> save(Book book, HttpMethod httpMethod);

    ResponseEntity delete(long id);
}