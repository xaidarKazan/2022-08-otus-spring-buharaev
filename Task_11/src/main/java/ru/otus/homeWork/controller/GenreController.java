package ru.otus.homeWork.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.dto.GenreDto;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;
import ru.otus.homeWork.service.SequenceGeneratorService;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepo;

    private final BookRepository bookRepo;

    private final SequenceGeneratorService service;

    @GetMapping("api/genres")
    public Flux<GenreDto> genres() {
        return genreRepo.findAll().map(GenreDto::toDto);
    }

    @PostMapping(value = "/api/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenreDto>> saveNew(@RequestBody Mono<GenreDto> genreDtoMono) {
       return  genreDtoMono.map(GenreDto::toEntity)
                           .doOnNext(genre -> genre.setId(service.getNextSequence(Genre.SEQUENCE_NAME)))
                           .flatMap(genreRepo::save)
                           .map(GenreDto::toDto)
                           .map(ResponseEntity::ok)
                           .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PutMapping(value = "/api/genre", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<GenreDto>> saveEdit(@RequestBody Mono<GenreDto> genreDtoMono) {
        return genreDtoMono.map(GenreDto::toEntity)
                           .flatMap(genreRepo::save)
                           .map(GenreDto::toDto)
                           .map(ResponseEntity::ok)
                           .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/api/genre/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        bookRepo.deleteByGenreId(id);
        return genreRepo.deleteById(id);
    }
}