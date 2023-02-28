package ru.otus.homeWork.service;

import org.springframework.http.ResponseEntity;
import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreService {

    ResponseEntity<List<Genre>> findAll();

    Genre findById(Long id);

    ResponseEntity<Genre> save(Genre author);

    ResponseEntity delete(long id);
}