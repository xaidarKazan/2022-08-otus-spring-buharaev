package ru.otus.homeWork.repositories;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.homeWork.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query("{book : ?0}")
    List<Comment> findByBookId(String id);

    @DeleteQuery("{book : ?0}")
    void deleteByBookId(String id);
}