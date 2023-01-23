package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.GenreRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepo;

    @GetMapping("api/genres")
    public List<Genre> genres() {
        return genreRepo.findAll();
    }

    @PostMapping("/api/genre")
    public Genre saveNew(@RequestBody Genre genre) {
        return genreRepo.save(genre);
    }

    @PutMapping("/api/genre")
    public Genre saveEdit(@RequestBody Genre genre) {
        Genre editedGenre = genreRepo.findById(genre.getId()).orElseThrow();
        editedGenre.setName(genre.getName());
        return genreRepo.save(editedGenre);
    }

    @DeleteMapping("/api/genre/{id}")
    public void delete(@PathVariable long id) {
        Genre genre = genreRepo.findById(id).orElseThrow();
        genreRepo.delete(genre);
    }

    @GetMapping("api/genres/accessToEdit")
    public void hasAccessToEdit() {}

    @GetMapping("/api/genres/accessToDelete")
    public void hasAccessToDelete() {}
}