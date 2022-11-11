package ru.otus.homeWork.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeWork.domain.Author;

public interface AuthorRepository extends MongoRepository<Author,String> {}