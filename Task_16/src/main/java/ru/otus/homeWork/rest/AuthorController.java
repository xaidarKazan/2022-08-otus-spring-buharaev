package ru.otus.homeWork.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.repositories.AuthorRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepo;

    @GetMapping("/api/authors")
    public List<Author> authors() {
        return authorRepo.findAll();
    }

    @PostMapping("/api/author")
    public Author saveNew(@RequestBody Author author) {
        return authorRepo.save(author);
    }

    @PutMapping("/api/author")
    public Author saveEdit(@RequestBody Author author) {
        Author editedAuthor = authorRepo.findById(author.getId()).orElseThrow();
        editedAuthor.setFirstName(author.getFirstName());
        editedAuthor.setLastName(author.getLastName());
        return authorRepo.save(editedAuthor);
    }

    @DeleteMapping("/api/author/{id}")
    public void delete(@PathVariable long id) {
        Author author = authorRepo.findById(id).orElseThrow();
        authorRepo.delete(author);
    }
}