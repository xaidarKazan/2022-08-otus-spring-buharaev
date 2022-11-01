package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homeWork.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("select count(g) from Genre g")
    long getGenresCount();
}