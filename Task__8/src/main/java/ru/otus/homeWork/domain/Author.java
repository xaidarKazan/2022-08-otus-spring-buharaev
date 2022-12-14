package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Author")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return "Author { id= '" + id + "', \n" +
               "         firstName= '" + firstName + "', \n" +
               "         lastName= '" + lastName + "', \n" +
               "       } ";
    }
}