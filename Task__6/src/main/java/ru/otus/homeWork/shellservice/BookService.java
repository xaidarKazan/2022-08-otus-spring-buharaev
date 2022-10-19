package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;


@ShellComponent
@ShellCommandGroup(value = "CRUD for book")
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;

    @Transactional(readOnly = true)
    @ShellMethod(value = "Books count", key = "b_count")
    public void getBooksCount() {
        System.out.println(bookRepo.getBooksCount());
    }

    @Transactional
    @ShellMethod(value = "Save new book", key = "b_save")
    public void save(String name, Integer publishingYear, Long author_id, Long genre_id) {
        Author author = authorRepo.getById(author_id);
        if(author == null) {
            System.out.printf("Author with id=%d is not exists\n", author_id);
            return;
        }
        Genre genre = genreRepo.getById(genre_id);
        if(genre == null) {
            System.out.printf("Genre with id=%d is not exists\n",genre_id);
            return;
        }
        Book book = new Book();
        book.setName(name);
        book.setPublishingYear(publishingYear);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepo.save(book);
        System.out.println("new book is saved");
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get book by Id number", key = "b_get")
    public void getById(long id) {
        Book book = bookRepo.getById(id);
        if(book == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(book);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all books", key = "b_getAll")
    public void getAll() {
        bookRepo.getAll().stream().forEach(System.out::println);
    }

    @Transactional
    @ShellMethod(value = "Delete book by Id number", key = "b_delete")
    public void deleteById(long id) {
        bookRepo.deleteById(id);
        System.out.println("The book is deleted");
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all comments", key = "b_getAllComments")
    public void getAllComments(long id) {
        Book book = bookRepo.getById(id);
        if(book != null ) {
            book.getComments().stream().forEach(System.out::println);
        }
        else
            System.out.println("Wrong id. Record is missing");
    }
}