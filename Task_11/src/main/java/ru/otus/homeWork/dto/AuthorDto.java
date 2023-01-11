package ru.otus.homeWork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.homeWork.domain.Author;

@Data
@AllArgsConstructor
public class AuthorDto {
    private String id;

    private String firstName;

    private String lastName;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }

    public static Author toEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getFirstName(), authorDto.getLastName());
    }
}