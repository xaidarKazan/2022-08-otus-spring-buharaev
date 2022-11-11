package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "Book")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}