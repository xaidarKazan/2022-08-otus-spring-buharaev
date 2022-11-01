package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homeWork.domain.Author;


public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select count(a) from Author a ")
    long getAuthorsCount();
}