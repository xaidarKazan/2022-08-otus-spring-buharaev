package ru.otus.homeWork.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homeWork.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author,String> {}