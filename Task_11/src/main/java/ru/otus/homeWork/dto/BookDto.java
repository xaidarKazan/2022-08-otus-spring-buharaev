package ru.otus.homeWork.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.homeWork.domain.Book;

@Data
@AllArgsConstructor
public class BookDto {

    private String id;

    private String name;

    private Integer publishingYear;

    @JsonProperty("author")
    private AuthorDto authorDto;

    @JsonProperty("genre")
    private GenreDto genreDto;

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getPublishingYear(),
                            AuthorDto.toDto(book.getAuthor()), GenreDto.toDto(book.getGenre()));
    }

    public static Book toEntity(BookDto bookDto) {
        return new Book(bookDto.getId(), bookDto.getName(), bookDto.getPublishingYear(),
                        AuthorDto.toEntity(bookDto.getAuthorDto()), GenreDto.toEntity(bookDto.getGenreDto()));
    }
}