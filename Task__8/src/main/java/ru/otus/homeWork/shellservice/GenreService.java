package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;
import ru.otus.homeWork.uiservice.GenreUIService;

import java.util.List;

@ShellComponent
@ShellCommandGroup(value = "CRUD for genre")
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepo;

    private final BookRepository bookRepo;

    private final GenreUIService uiService;

    @ShellMethod(value = "Get genre by id", key = "g_getById")
    public void getById() {
        Genre genre = genreRepo.findById(uiService.getId()).orElse(null);
        uiService.print(genre);
    }

    @ShellMethod(value = "Genres count", key = "g_count")
    public void getGenresCount() {
        uiService.print(genreRepo.count());
    }

    @Transactional
    @ShellMethod(value = "Save new genre", key = "g_save")
    public void save() {
        Genre genre = uiService.save();
        boolean isSaved = genreRepo.save(genre) != null;
        uiService.isSaved(isSaved);
    }

    @Transactional
    @ShellMethod(value = "Update genre", key = "g_update")
    public void update() {
        Genre genre = genreRepo.findById(uiService.getId()).orElse(null);
        boolean isUpdate = uiService.update(genre) != null;
        if(isUpdate) {
            genreRepo.save(genre);
        }
        uiService.isSaved(isUpdate);
    }

    @ShellMethod(value = "Get all genres", key = "g_getAll")
    public void getAll() {
        List<Genre> genres = genreRepo.findAll();
        uiService.print(genres);
    }

    @Transactional
    @ShellMethod(value = "Delete genre by id", key = "g_delete")
    public void deleteById() {
        Genre genre = genreRepo.findById(uiService.getId()).orElse(null);
        if(genre != null) {
            bookRepo.deleteByGenreId(genre.getId());
            genreRepo.delete(genre);
        }
        else {
            uiService.print(genre);
        }
    }
}