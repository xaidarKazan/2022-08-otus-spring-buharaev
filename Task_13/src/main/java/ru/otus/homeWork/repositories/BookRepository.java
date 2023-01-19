package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homeWork.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}