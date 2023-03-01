package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.service.GenreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("api/genres")
    public ResponseEntity<List<Genre>> genres() {
        return ResponseEntity.ok(genreService.findAll());
    }

    @PostMapping("/api/genre")
    public ResponseEntity<Genre> saveNew(@RequestBody Genre genre) {

        System.out.println("saveNew");
        return ResponseEntity.ok(genreService.save(genre));
    }

    @PutMapping("/api/genre")
    public ResponseEntity<Genre> saveEdit(@RequestBody Genre genre) {
        System.out.println("saveEdit");
        return ResponseEntity.ok(genreService.save(genre));
    }

    @DeleteMapping("/api/genre/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        genreService.delete(id);
        return ResponseEntity.ok().build();
    }
}