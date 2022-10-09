package ru.otus.homeWork.dao;

import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreDao {

    long getGenresCount();

    void insert(Genre genre);

    Genre getById(long id);

    List<Genre> getAll();

    void deleteById(long id);
}