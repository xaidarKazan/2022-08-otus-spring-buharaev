package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.uiservice.AuthorUIService;

import java.util.List;

@ShellComponent
@ShellCommandGroup(value = "CRUD for author")
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepo;

    private final BookRepository bookRepo;

    private final AuthorUIService uiService;

    @ShellMethod(value = "Get author by id", key = "a_getById")
    public void getById() {
        String id = uiService.getId();
        Author author = authorRepo.findById(id).orElse(null);
        uiService.print(author);
    }

    @ShellMethod(value = "Authors count", key = "a_count")
    public void authorsCount() {
        uiService.print(authorRepo.count());
    }

    @Transactional
    @ShellMethod(value = "Save new author", key = "a_save")
    public void save() {
        Author author = uiService.save();
        boolean isSaved = authorRepo.save(author) != null;
        uiService.isSaved(isSaved);
    }

    @Transactional
    @ShellMethod(value = "Update author", key = "a_update")
    public void update() {
        String id = uiService.getId();
        Author author = authorRepo.findById(id).orElse(null);
        boolean isUpdate = uiService.update(author) != null;
        if(isUpdate) {
            authorRepo.save(author);
        }
        uiService.isSaved(isUpdate);
    }

    @ShellMethod(value = "Get all authors", key = "a_getAll")
    public void getAll() {
        List<Author> authors = authorRepo.findAll();
        uiService.print(authors);
    }

    @Transactional
    @ShellMethod(value = "Delete author by id", key = "a_delete")
    public void deleteById() {
        String id = uiService.getId();
        Author author = authorRepo.findById(id).orElse(null);
        if(author != null) {
            bookRepo.deleteByAuthorId(author.getId());
            authorRepo.delete(author);
        }
        else {
            uiService.print(author);
        }
    }
}