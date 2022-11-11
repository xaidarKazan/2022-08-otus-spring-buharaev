package ru.otus.homeWork.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "Comment")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    private String content;

    @DocumentReference(collection = "Book")
    private Book book;

    @Override
    public String toString() {
        return "Comment { id= '" + id + "', \n " +
               "          content='" + content + "', \n " +
               "        " + book + " \n " +
               "        }";
    }
}