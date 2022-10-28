package ru.otus.homeWork.repositories;

import ru.otus.homeWork.domain.Comment;

public interface CommentRepository {

    Comment save(Comment comment);

    Comment getById(long id);

    void deleteById(long id);
}