package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.service.AuthorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/api/authors")
    public ResponseEntity<List<Author>> authors() {
        return authorService.findAll();
    }

    @PostMapping("/api/author")
    public ResponseEntity<Author> saveNew(@RequestBody Author author) {
        return authorService.save(author, HttpMethod.POST);
    }

    @PutMapping("/api/author")
    public ResponseEntity<Author> saveEdit(@RequestBody Author author) {
        return authorService.save(author, HttpMethod.PUT);
    }

    @DeleteMapping("/api/author/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        return authorService.delete(id);
    }
}