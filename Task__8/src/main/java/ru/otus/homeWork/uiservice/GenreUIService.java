package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreUIService {

    String getId();

    String getName();

    Genre save();

    Genre update(Genre genre);

    void isSaved(boolean isSaved);

    void print(Genre genre);

    void print(List<Genre> genres);

    void print(long count);
}