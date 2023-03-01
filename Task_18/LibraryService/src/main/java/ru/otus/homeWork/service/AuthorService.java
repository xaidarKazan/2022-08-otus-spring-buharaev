package ru.otus.homeWork.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorService {

    ResponseEntity<List<Author>> findAll();

    ResponseEntity<Author> save(Author author, HttpMethod httpMethod);


    ResponseEntity delete(long id);
}