package ru.otus.homeWork.uiservice;

import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Comment;

import java.util.List;

public interface CommentUIService {

    String getId();

    String getContent();

    Comment save();

    void isSaved(boolean isSaved);

    void isExists(Book book);

    void print(Comment comment);

    void print(List<Comment> comments);

    void print(long count);
}
