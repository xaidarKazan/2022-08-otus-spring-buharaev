package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homeWork.repositories.BookDao;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;

@ShellComponent
@ShellCommandGroup(value = "CRUD for book")
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;

    @ShellMethod(value = "Books count", key = "b_count")
    public void getBooksCount() {
        System.out.println(bookDao.getBooksCount());
    }

    @ShellMethod(value = "Save new book", key = "b_save")
    public void insert(String name, Integer publishingYear, Long author_id, Long genre_id) {
        Author author = Author.builder().id(author_id).build();
        Genre genre = Genre.builder().id(genre_id).build();
        Book book = Book.builder()
                        .name(name)
                        .publishingYear(publishingYear)
                        .author(author)
                        .genre(genre)
                        .build();
        bookDao.insert(book);
        System.out.println("new book is saved");
    }

    @ShellMethod(value = "Get book by Id number", key = "b_get")
    public void getById(long id) {
        System.out.println(bookDao.getById(id));
    }

    @ShellMethod(value = "Get all books", key = "b_getAll")
    public void getAll() {
        bookDao.getAll().stream().forEach(System.out::println);
    }

    @ShellMethod(value = "Detele book by Id number", key = "b_delete")
    public void deleteById(long id) {
        bookDao.deleteById(id);
        System.out.println("The book is deleted");
    }
}