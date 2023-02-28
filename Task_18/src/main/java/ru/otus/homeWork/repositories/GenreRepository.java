package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeWork.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}