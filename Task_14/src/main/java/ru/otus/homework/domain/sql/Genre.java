package ru.otus.homework.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GENRE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public ru.otus.homework.domain.nosql.Genre toNoSqlDomain() {
        return new ru.otus.homework.domain.nosql.Genre(Long.toHexString(getId()), getName());
    }
}