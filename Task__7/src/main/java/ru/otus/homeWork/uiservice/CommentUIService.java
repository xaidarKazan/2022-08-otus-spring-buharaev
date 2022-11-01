package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Comment;

import java.util.List;

public interface CommentUIService {

    Long getId();

    Comment save();

    void isSaved(boolean isSaved);

    void isExists(Book book, Long id);

    void print(Comment comment);

    void print(List<Comment> comments);

    void print(long count);
}
