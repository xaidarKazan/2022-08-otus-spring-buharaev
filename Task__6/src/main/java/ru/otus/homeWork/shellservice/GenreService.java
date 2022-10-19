package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.GenreRepository;

import javax.transaction.Transactional;

@ShellComponent
@ShellCommandGroup(value = "CRUD for genre")
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepo;

    @ShellMethod(value = "Genres count", key = "g_count")
    public void getGenresCount() {
        System.out.println(genreRepo.getGenresCount());
    }

    @Transactional
    @ShellMethod(value = "Save new genre", key = "g_save")
    public void save(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        genreRepo.save(genre);
        System.out.println("new genre is saved");
    }

    @ShellMethod(value = "Get genre by Id number", key = "g_get")
    public void getById(long id) {
        Genre genre = genreRepo.getById(id);
        if(genre == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(genre);
    }

    @ShellMethod(value = "Get all genres", key = "g_getAll")
    public void getAll() {
        genreRepo.getAll().stream().forEach(System.out::println);
    }

    @Transactional
    @ShellMethod(value = "Delete genre by Id number", key = "g_delete")
    public void deleteById(long id) {
        genreRepo.deleteById(id);
        System.out.println("The genre is deleted");
    }
}