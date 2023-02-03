package ru.otus.homework.domain.nosql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private String id;

    private String name;

    private Integer publishingYear;

    @DocumentReference(collection = "Author")
    private Author author;

    @DocumentReference(collection = "Genre")
    private Genre genre;

    @DocumentReference(collection = "Comment", lazy = true)
    private List<Comment> comments;

    @Override
    public String toString() {
        return  "Book { id='" + id + "', \n" +
                "       name='" + name + "', \n" +
                "       publishingYear=" + publishingYear + "', \n" +
                "       " + author + ", \n" +
                "       " + genre + " \n" +
                "     }";
    }

    public ru.otus.homework.domain.sql.Book toSqlDomain() {
        return ru.otus.homework.domain.sql.Book.builder()
                                                .id(Long.parseLong(getId(), 16))
                                                .name(getName())
                                                .publishingYear(getPublishingYear())
                                                .author(getAuthor().toSqlDomain())
                                                .genre(getGenre().toSqlDomain())
                                                .comments(getComments().stream().map(comment -> comment.toSqlDomain()).collect(Collectors.toList()))
                                                .build();
    }
}