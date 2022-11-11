package ru.otus.homeWork.repositories;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeWork.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {

    @DeleteQuery(value = "{author : ?0}")
    void deleteByAuthorId(String id);

    @DeleteQuery(value = "{genre : ?0}")
    void deleteByGenreId(String id);
}