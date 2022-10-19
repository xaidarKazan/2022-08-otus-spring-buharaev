package ru.otus.homeWork.repositories;

import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreRepository {

    long getGenresCount();

    Genre save(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}