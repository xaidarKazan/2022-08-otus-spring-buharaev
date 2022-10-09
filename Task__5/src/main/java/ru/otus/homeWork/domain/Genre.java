package ru.otus.homeWork.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Genre {

    private Long id;
    private String name;
}