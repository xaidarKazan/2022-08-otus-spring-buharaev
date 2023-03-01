package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
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
        return  ResponseEntity.ok(authorService.findAll());
//        return authorService.findAll();
    }

    @PostMapping("/api/author")
    public ResponseEntity<Author> saveNew(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.save(author));
    }

    @PutMapping("/api/author")
    public ResponseEntity<Author> saveEdit(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.save(author));
    }

    @DeleteMapping("/api/author/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }
}