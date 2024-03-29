package ru.otus.homework.domain.sql;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BOOK")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "publishingyear", nullable = false)
    private Integer publishingYear;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ToString.Exclude
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public ru.otus.homework.domain.nosql.Book toNoSqlDomain() {
        return ru.otus.homework.domain.nosql.Book
                .builder()
                .id(Long.toHexString(getId()))
                .name(getName())
                .publishingYear(getPublishingYear())
                .author(getAuthor().toNoSqlDomain())
                .genre(getGenre().toNoSqlDomain())
                .build();
    }
}