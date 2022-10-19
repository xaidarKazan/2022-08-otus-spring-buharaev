package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.repositories.AuthorRepositoryJpa;


@ShellComponent
@ShellCommandGroup(value = "CRUD for author")
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepositoryJpa authorRepo;

    @Transactional(readOnly = true)
    @ShellMethod(value = "Authors count", key = "a_count")
    public void authorsCount() {
        System.out.println(authorRepo.getAuthorsCount());
    }

    @Transactional
    @ShellMethod(value = "Save new author", key = "a_save")
    public void save(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepo.save(author);
        System.out.println("new author is saved");
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get author by Id number", key = "a_get")
    public void getById(long id) {
        Author author = authorRepo.getById(id);
        if(author == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(author);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all authors", key = "a_getAll")
    public void getAll() {
        authorRepo.getAll().stream().forEach(System.out::println);
    }


    @Transactional
    @ShellMethod(value = "Delete author by Id number", key = "a_delete")
    public void deleteById(long id) {
        authorRepo.deleteById(id);
        System.out.println("author is deleted");
    }
}