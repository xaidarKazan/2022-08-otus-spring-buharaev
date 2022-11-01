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
import ru.otus.homeWork.uiservice.BookUIService;

import java.util.List;


@ShellComponent
@ShellCommandGroup(value = "CRUD for book")
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final GenreRepository genreRepo;

    private final BookUIService uiService;

    @ShellMethod(value = "Books count", key = "b_count")
    public void getBooksCount() {
        uiService.print(bookRepo.getBooksCount());
    }

    @Transactional
    @ShellMethod(value = "Save new book", key = "b_save")
    public void save() {
        boolean isSaved;
        Book book = uiService.save();
        if(book == null) {
            uiService.isSaved(false);
            return;
        }

        Long author_id = book.getAuthor().getId();
        Author author = authorRepo.findById(author_id).orElse(null);
        uiService.isExists(author, author_id);

        Long genre_id = book.getGenre().getId();
        Genre genre = genreRepo.findById(genre_id).orElse(null);
        uiService.isExists(genre, genre_id);

        isSaved = author!= null && genre != null;
        if(isSaved) {
            book.setAuthor(author);
            book.setGenre(genre);
            bookRepo.save(book);
        }
        uiService.isSaved(isSaved);
    }

    @Transactional
    @ShellMethod(value = "Update book", key = "b_update")
    public void update() {
        Book book = null;
        Long id = uiService.getId();
        if(id != null) {
            book = bookRepo.findById(id).orElse(null);
        }
        boolean isUpdate = uiService.update(book);
        if (isUpdate) {
            bookRepo.save(book);
        }
        uiService.isSaved(isUpdate);
    }

    @ShellMethod(value = "Get book by Id number", key = "b_get")
    public void getById() {
        Long id = uiService.getId();
        if(id != null) {
            Book book = bookRepo.findById(id).orElse(null);
            uiService.print(book);
        }
    }

    @ShellMethod(value = "Get all books", key = "b_getAll")
    public void getAll() {
        List<Book> bookList = bookRepo.findAll();
        uiService.print(bookList);
    }

    @Transactional
    @ShellMethod(value = "Delete book by Id number", key = "b_delete")
    public void deleteById() {
        Long id = uiService.getId();
        if(id != null) {
            bookRepo.deleteById(id);
        }
    }
}