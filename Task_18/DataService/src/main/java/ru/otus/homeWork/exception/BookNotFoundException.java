package ru.otus.homeWork.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Книга не найдена.");
    }
}