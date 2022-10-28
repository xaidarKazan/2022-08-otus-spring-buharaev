package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.GenreRepository;
import ru.otus.homeWork.uiservice.GenreUIService;

import java.util.List;

@ShellComponent
@ShellCommandGroup(value = "CRUD for genre")
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepo;

    private final GenreUIService uiService;

    @ShellMethod(value = "Genres count", key = "g_count")
    public void getGenresCount() {
        uiService.print(genreRepo.getGenresCount());
    }

    @Transactional
    @ShellMethod(value = "Save new genre", key = "g_save")
    public void save() {
        Genre genre = uiService.save();
        boolean isSaved = genreRepo.save(genre) != null;
        uiService.isSaved(isSaved);
    }

    @ShellMethod(value = "Get genre by Id number", key = "g_get")
    public void getById() {
        Long id = uiService.getId();
        if(id != null) {
            Genre genre = genreRepo.getById(id);
            uiService.print(genre);
        }
    }

    @ShellMethod(value = "Get all genres", key = "g_getAll")
    public void getAll() {
        List<Genre> genres = genreRepo.getAll();
        uiService.print(genres);
    }

    @Transactional
    @ShellMethod(value = "Delete genre by Id number", key = "g_delete")
    public void deleteById() {
        Long id = uiService.getId();
        if(id != null) {
            genreRepo.deleteById(id);
        }
    }
}