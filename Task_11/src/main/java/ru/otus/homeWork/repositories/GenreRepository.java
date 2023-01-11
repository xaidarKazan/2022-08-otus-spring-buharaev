package ru.otus.homeWork.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.homeWork.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {}