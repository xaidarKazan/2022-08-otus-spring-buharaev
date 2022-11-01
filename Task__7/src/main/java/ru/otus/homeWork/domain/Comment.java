package ru.otus.homeWork.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
@Data
@NoArgsConstructor
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
}