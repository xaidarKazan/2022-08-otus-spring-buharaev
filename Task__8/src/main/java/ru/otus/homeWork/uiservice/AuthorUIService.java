package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Author;

import java.util.List;

public interface AuthorUIService {

    String getId();

    String getFirstName();

    String getLastName();

    Author save();

    Author update(Author author);

    void isSaved(boolean isSaved);

    void print(Author author);

    void print(List<Author> authors);

    void print(long count);
}