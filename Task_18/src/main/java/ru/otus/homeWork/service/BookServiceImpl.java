package ru.otus.homeWork.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.exception.AuthorNotFoundException;
import ru.otus.homeWork.exception.BookNotFoundException;
import ru.otus.homeWork.exception.GenreNotFoundException;
import ru.otus.homeWork.repositories.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookRepository bookRepo;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllBooks_Fallback"
    )
    @Override
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookRepo.findAll());
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveBook_Fallback",
            ignoreExceptions = { BookNotFoundException.class, AuthorNotFoundException.class, GenreNotFoundException.class }
    )
    @Override
    public ResponseEntity<Book> save(Book book) {
        Author author = authorService.findById(book.getAuthor().getId());
        Genre genre = genreService.findById(book.getGenre().getId());
        if(book.getId() != null) {
            Book editedBook = bookRepo.findById(book.getId()).orElseThrow(() -> new BookNotFoundException());
            editedBook.setName(book.getName());
            editedBook.setPublishingYear(book.getPublishingYear());
            book = editedBook;
        }
        book.setAuthor(author);
        book.setGenre(genre);
        return ResponseEntity.ok(bookRepo.save(book));
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "bookDelete_Fallback",
            ignoreExceptions = { BookNotFoundException.class}
    )
    @Override
    public ResponseEntity delete(long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException());
        bookRepo.delete(book);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<Book>> findAllBooks_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity<Book> saveBook_Fallback(Book book) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity bookDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}