package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    private String id;

    private String name;

    @Override
    public String toString() {
        return "Genre { id= '" + id + "', \n" +
               "        name= '" + name + "', \n" +
               "      } ";
    }
}