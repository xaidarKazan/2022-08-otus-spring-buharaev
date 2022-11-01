package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorUIService {

    Long getId();

    Author save();

    void isSaved(boolean isSaved);

    void print(Author author);

    void print(List<Author> authors);

    void print(long count);
}