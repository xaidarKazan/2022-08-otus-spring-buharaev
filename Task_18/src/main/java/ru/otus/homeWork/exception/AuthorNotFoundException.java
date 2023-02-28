package ru.otus.homeWork.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Автор не найден.");
    }
}