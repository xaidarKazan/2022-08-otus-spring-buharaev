package ru.otus.homeWork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.exception.GenreNotFoundException;
import ru.otus.homeWork.repositories.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepo;

    @Override
    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepo.findById(id).orElseThrow(() -> new GenreNotFoundException());
    }

    @Override
    public Genre save(Genre genre) {
        if(genre.getId() != null) {
            Genre editedGenre = genreRepo.findById(genre.getId()).orElseThrow(() -> new GenreNotFoundException());
            editedGenre.setName(genre.getName());
            genre = editedGenre;
        }
        return genreRepo.save(genre);
    }

    @Override
    public void delete(long id) {
        Genre genre = genreRepo.findById(id).orElseThrow(() -> new GenreNotFoundException());
        genreRepo.delete(genre);
    }
}