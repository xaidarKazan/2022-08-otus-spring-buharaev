package ru.otus.homeWork.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.exception.GenreNotFoundException;
import ru.otus.homeWork.repositories.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepo;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllGenres_Fallback"
    )
    @Override
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok(genreRepo.findAll());
    }

    @Override
    public Genre findById(Long id) {
        return genreRepo.findById(id).orElseThrow(() -> new GenreNotFoundException());
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveGenre_Fallback",
            ignoreExceptions = { GenreNotFoundException.class }
    )
    @Override
    public ResponseEntity<Genre> save(Genre genre) {
        if(genre.getId() != null) {
            Genre editedGenre = genreRepo.findById(genre.getId()).orElseThrow(() -> new GenreNotFoundException());
            editedGenre.setName(genre.getName());
            genre = editedGenre;
        }
        return ResponseEntity.ok(genreRepo.save(genre));
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "genreDelete_Fallback",
            ignoreExceptions = { GenreNotFoundException.class }
    )
    @Override
    public ResponseEntity delete(long id) {
        Genre genre = genreRepo.findById(id).orElseThrow(() -> new GenreNotFoundException());
        genreRepo.delete(genre);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<Genre>> findAllGenres_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity<Genre> saveGenre_Fallback(Genre genre) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity genreDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}