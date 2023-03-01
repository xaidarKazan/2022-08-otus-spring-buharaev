package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.service.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("api/books")
    public ResponseEntity<List<Book>> books() {
        return bookService.findAll();
    }

    @PostMapping("/api/book")
    public ResponseEntity<Book> saveNew(@RequestBody Book book) {
        return bookService.save(book, HttpMethod.POST);
    }

    @PutMapping("/api/book")
    public ResponseEntity<Book> saveEdit(@RequestBody Book book) {
        return bookService.save(book, HttpMethod.PUT);
    }

    @DeleteMapping("/api/book/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return bookService.delete(id);
    }
}