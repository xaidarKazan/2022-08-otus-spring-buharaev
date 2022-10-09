package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeWork.dao.GenreDao;
import ru.otus.homeWork.domain.Genre;

@ShellComponent
@ShellCommandGroup(value = "CRUD for genre")
@RequiredArgsConstructor
public class GenreService {

    private final GenreDao genreDao;

    @ShellMethod(value = "Genres count", key = "g_count")
    public void getGenresCount() {
        System.out.println(genreDao.getGenresCount());
    }

    @ShellMethod(value = "Save new genre", key = "g_save")
    public void insert(String name) {
        Genre genre = Genre.builder()
                           .name(name)
                           .build();
        genreDao.insert(genre);
        System.out.println("new genre is saved");
    }

    @ShellMethod(value = "Get genre by Id number", key = "g_get")
    public void getById(long id) {
        System.out.println(genreDao.getById(id));
    }

    @ShellMethod(value = "Get all genres", key = "g_getAll")
    public void getAll() {
        genreDao.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod(value = "Detele genre by Id number", key = "g_delete")
    public void deleteById(long id) {
        genreDao.deleteById(id);
        System.out.println("The genre is deleted");
    }
}