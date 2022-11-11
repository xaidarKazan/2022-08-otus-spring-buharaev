package ru.otus.homeWork.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homeWork.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {}