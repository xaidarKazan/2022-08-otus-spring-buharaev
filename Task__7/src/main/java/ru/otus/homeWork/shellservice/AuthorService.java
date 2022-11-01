package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.uiservice.AuthorUIService;

import java.util.List;


@ShellComponent
@ShellCommandGroup(value = "CRUD for author")
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepo;

    private final AuthorUIService uiService;

    @ShellMethod(value = "Authors count", key = "a_count")
    public void authorsCount() {
        uiService.print(authorRepo.getAuthorsCount());
    }

    @Transactional
    @ShellMethod(value = "Save new author", key = "a_save")
    public void save() {
        Author author = uiService.save();
        authorRepo.save(author);
    }

    @ShellMethod(value = "Get author by Id number", key = "a_get")
    public void getById() {
        Long id = uiService.getId();
        if(id != null) {
            Author author = authorRepo.findById(id).orElse(null);
            uiService.print(author);
        }
    }

    @ShellMethod(value = "Get all authors", key = "a_getAll")
    public void getAll() {
        List<Author> authors = authorRepo.findAll();
        uiService.print(authors);
    }

    @Transactional
    @ShellMethod(value = "Delete author by Id number", key = "a_delete")
    public void deleteById() {
        Long id = uiService.getId();
        if(id != null){
            authorRepo.deleteById(id);
        }
    }
}