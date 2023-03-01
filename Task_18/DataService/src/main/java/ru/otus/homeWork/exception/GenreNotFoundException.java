package ru.otus.homeWork.exception;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("Жанр не найден.");
    }
}