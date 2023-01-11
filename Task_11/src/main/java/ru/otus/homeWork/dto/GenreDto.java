package ru.otus.homeWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.homeWork.domain.Genre;

@Data
@AllArgsConstructor
public class GenreDto {

    private String id;

    private String name;

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public static Genre toEntity(GenreDto genreDto) {
        return new Genre(genreDto.getId(), genreDto.getName());
    }
}