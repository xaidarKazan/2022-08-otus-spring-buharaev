package ru.otus.homeWork.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("db_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbSequence {

    @Id
    private String id;
    private long sequenceNumber;
}