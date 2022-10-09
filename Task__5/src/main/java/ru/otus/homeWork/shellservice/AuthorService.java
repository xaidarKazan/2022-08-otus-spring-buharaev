package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeWork.dao.AuthorDao;
import ru.otus.homeWork.domain.Author;

@ShellComponent
@ShellCommandGroup(value = "CRUD for author")
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    @ShellMethod(value = "Authors count", key = "a_count")
    public void authorsCount() {
        System.out.println(authorDao.getAuthorsCount());
    }

    @ShellMethod(value = "Save new author", key = "a_save")
    public void insert(String firstName, String lastName) {
        Author author = Author.builder()
                              .firstName(firstName)
                              .lastName(lastName)
                              .build();
        authorDao.insert(author);
        System.out.println("new author is saved");
    }

    @ShellMethod(value = "Get author by Id number", key = "a_get")
    public void getById(long id) {
        System.out.println(authorDao.getById(id));
    }

    @ShellMethod(value = "Get all authors", key = "a_getAll")
    public void getAll() {
        authorDao.getAll().stream().forEach(System.out::println);
    }


    @ShellMethod(value = "Detele author by Id number", key = "a_delete")
    public void deleteById(long id) {
        authorDao.deleteById(id);
        System.out.println("author is deleted");
    }
}