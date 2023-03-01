package ru.otus.homeWork.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreService {

    ResponseEntity<List<Genre>> findAll();

    ResponseEntity<Genre> save(Genre author, HttpMethod httpMethod);

    ResponseEntity delete(long id);
}