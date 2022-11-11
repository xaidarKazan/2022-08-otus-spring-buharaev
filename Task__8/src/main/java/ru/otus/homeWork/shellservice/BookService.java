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
import ru.otus.homeWork.repositories.CommentRepository;
import ru.otus.homeWork.repositories.GenreRepository;
import ru.otus.homeWork.uiservice.AuthorUIService;
import ru.otus.homeWork.uiservice.BookUIService;
import ru.otus.homeWork.uiservice.GenreUIService;

import java.util.List;


@ShellComponent
@ShellCommandGroup(value = "CRUD for book")
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;

    private final CommentRepository commentRepo;

    private final BookUIService bookUiService;
    private final AuthorUIService authorUIService;
    private final GenreUIService genreUIService;

    @ShellMethod(value = "Books count", key = "b_count")
    public void getBooksCount() {
        bookUiService.print(bookRepo.count());
    }

    @Transactional
    @ShellMethod(value = "Save new book", key = "b_save")
    public void save() {
        boolean isSaved;
        Book book = bookUiService.save();

        String id = authorUIService.getId();
        Author author = authorRepo.findById(id).orElse(null);
        bookUiService.isExists(author);

        Genre genre = genreRepo.findById(genreUIService.getId()).orElse(null);
        bookUiService.isExists(genre);

        isSaved = author!= null && genre != null;
        if(isSaved) {
            book.setAuthor(author);
            book.setGenre(genre);
            bookRepo.save(book);
        }
        bookUiService.isSaved(isSaved);
    }

    @Transactional
    @ShellMethod(value = "Update book", key = "b_update")
    public void update() {
        Book book = bookRepo.findById(bookUiService.getId()).orElse(null);
        boolean isUpdate = bookUiService.update(book);
        if(book != null) {
            Author author = authorUIService.update(book.getAuthor());
            Genre genre = genreUIService.update(book.getGenre());
            isUpdate = author!= null && genre != null;
        }
        if(isUpdate) {
            bookRepo.save(book);
        }
        bookUiService.isSaved(isUpdate);
    }

    @ShellMethod(value = "Get book by id", key = "b_getById")
    public void getById() {
        Book book = bookRepo.findById(bookUiService.getId()).orElse(null);
        bookUiService.print(book);
    }

    @ShellMethod(value = "Get all books", key = "b_getAll")
    public void getAll() {
        List<Book> bookList = bookRepo.findAll();
        bookUiService.print(bookList);
    }

    @Transactional
    @ShellMethod(value = "Delete book by id number", key = "b_delete")
    public void deleteById() {
        Book book = bookRepo.findById(bookUiService.getId()).orElse(null);
        if(book != null) {
            commentRepo.deleteByBookId(book.getId());
            bookRepo.delete(book);
        }
        else {
            bookUiService.print(book);
        }
    }
}