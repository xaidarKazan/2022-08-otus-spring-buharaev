package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepo;

    private final AuthorRepository authorRepo;

    private final GenreRepository genreRepo;

    @GetMapping("api/books")
    public List<Book> books() {
        return bookRepo.findAll();
    }

    @PostMapping("/api/book")
    public Book saveNew(@RequestBody Book book) {
        Author author = authorRepo.findById(book.getAuthor().getId()).orElseThrow();
        Genre genre = genreRepo.findById(book.getGenre().getId()).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepo.save(book);
    }

    @PutMapping("/api/book")
    public Book saveEdit(@RequestBody Book book) {
        Book editedBook = bookRepo.findById(book.getId()).orElseThrow();
        Author author = authorRepo.findById(book.getAuthor().getId()).orElseThrow();
        Genre genre = genreRepo.findById(book.getGenre().getId()).orElseThrow();
        editedBook.setName(book.getName());
        editedBook.setPublishingYear(book.getPublishingYear());
        editedBook.setAuthor(author);
        editedBook.setGenre(genre);
        return bookRepo.save(editedBook);
    }

    @DeleteMapping("/api/book/{id}")
    public void delete(@PathVariable long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        bookRepo.delete(book);
    }

    @GetMapping("api/books/accessToEdit")
    public void hasAccessToEdit() {}

    @GetMapping("/api/books/accessToDelete")
    public void hasAccessToDelete() {}
}