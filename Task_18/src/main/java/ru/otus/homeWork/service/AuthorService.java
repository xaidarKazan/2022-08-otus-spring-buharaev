package ru.otus.homeWork.service;

import org.springframework.http.ResponseEntity;
import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorService {
    ResponseEntity<List<Author>> findAll();

    Author findById(Long id);

    ResponseEntity<Author> save(Author author);

    ResponseEntity delete(long id);
}