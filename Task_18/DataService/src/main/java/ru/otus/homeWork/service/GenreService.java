package ru.otus.homeWork.service;

import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(Genre author);

    void delete(long id);
}