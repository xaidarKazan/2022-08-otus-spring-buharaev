package ru.otus.homeWork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.homeWork.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select count(b) from Book b")
    long getBooksCount();
}