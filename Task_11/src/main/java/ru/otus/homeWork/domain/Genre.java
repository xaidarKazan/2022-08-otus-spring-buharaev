package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Genre")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Transient
    public static final String SEQUENCE_NAME = "sq_genre";

    @Id
    private String id;

    private String name;
}