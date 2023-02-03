package ru.otus.homework.domain.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="CONTENT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public ru.otus.homework.domain.nosql.Comment toNoSqlDomain() {
        return new ru.otus.homework.domain.nosql.Comment(Long.toHexString(getId()), getContent(), getBook().toNoSqlDomain());
    }
}