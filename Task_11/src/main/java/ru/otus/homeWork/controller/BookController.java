package ru.otus.homeWork.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.dto.BookDto;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;
import ru.otus.homeWork.service.SequenceGeneratorService;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepo;

    private final AuthorRepository authorRepo;

    private final GenreRepository genreRepo;

    private final SequenceGeneratorService service;

    @GetMapping("api/books")
    public Flux<BookDto> books() {
        return bookRepo.findAll().map(BookDto::toDto);
    }

    @PostMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BookDto>> saveNew(@RequestBody Mono<BookDto> bookDtoMono) {
        return bookDtoMono.map(BookDto::toEntity)
                          .doOnNext(book -> {
                                        book.setId(service.getNextSequence(Book.SEQUENCE_NAME));
                                    })
                          .flatMap(bookRepo::save)
                          .map(BookDto::toDto)
                          .map(ResponseEntity::ok)
                          .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PutMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BookDto>> saveEdit(@RequestBody Mono<BookDto> bookDtoMono) {
        return bookDtoMono.map(BookDto::toEntity)
                          .flatMap(bookRepo::save)
                          .map(BookDto::toDto)
                          .map(ResponseEntity::ok)
                          .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/api/book/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return bookRepo.deleteById(id);
    }
}