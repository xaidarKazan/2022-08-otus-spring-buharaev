package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeWork.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {}