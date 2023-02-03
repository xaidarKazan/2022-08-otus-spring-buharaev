package ru.otus.homework.domain.nosql;

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

    public ru.otus.homework.domain.sql.Author toSqlDomain() {
        return new ru.otus.homework.domain.sql.Author(Long.parseLong(getId(), 16), getFirstName(), getLastName());
    }
}