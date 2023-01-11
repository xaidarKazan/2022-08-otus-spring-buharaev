package ru.otus.homeWork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.dto.AuthorDto;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.service.SequenceGeneratorService;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepo;

    private final BookRepository bookRepo;

    private final SequenceGeneratorService service;


    @GetMapping("/api/authors")
    public Flux<AuthorDto> all() {
        return authorRepo.findAll().map(author -> AuthorDto.toDto(author));
    }

    @PostMapping(value = "/api/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AuthorDto>> saveNew(@RequestBody Mono<AuthorDto> authorDtoMono) {
        return authorDtoMono.map(AuthorDto::toEntity)
                            .doOnNext(author -> author.setId(service.getNextSequence(Author.SEQUENCE_NAME)))
                            .flatMap(authorRepo::save)
                            .map(AuthorDto::toDto)
                            .map(ResponseEntity::ok)
                            .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));
    }

    @PutMapping(value = "/api/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<AuthorDto>> saveEdit(@RequestBody Mono<AuthorDto> authorDtoMono) {
        return authorDtoMono.map(AuthorDto::toEntity)
                            .flatMap(author -> authorRepo.findById(author.getId())
                                                         .doOnNext(editeAuthor -> {
                                                             editeAuthor.setFirstName(author.getFirstName());
                                                             editeAuthor.setLastName(author.getLastName());
                                                         }))
                            .map(AuthorDto::toDto)
                            .map(ResponseEntity::ok)
                            .switchIfEmpty(Mono.fromCallable(() -> ResponseEntity.notFound().build()));

    }

    @DeleteMapping("/api/author/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        bookRepo.deleteByAuthorId(id);
        return authorRepo.deleteById(id) ;
    }
}