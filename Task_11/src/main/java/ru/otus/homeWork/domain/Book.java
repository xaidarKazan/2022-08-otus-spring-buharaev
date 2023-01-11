package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Transient
    public static final String SEQUENCE_NAME = "sq_book";

    @Id
    private String id;

    private String name;

    private Integer publishingYear;

    @DBRef(db = "Author")
    private Author author;

    @DBRef(db = "Genre")
    private Genre genre;
}