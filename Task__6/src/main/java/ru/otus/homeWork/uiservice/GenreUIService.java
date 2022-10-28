package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Genre;

import java.util.List;

public interface GenreUIService {

    Long getId();

    Genre save();

    void isSaved(boolean isSaved);

    void print(Genre genre);

    void print(List<Genre> genres);

    void print(long count);
}