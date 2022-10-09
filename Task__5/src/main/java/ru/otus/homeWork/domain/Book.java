package ru.otus.homeWork.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Book {

    private Long id;
    private String name;
    private Integer publishingYear;
    private Author author;
    private Genre genre;
}