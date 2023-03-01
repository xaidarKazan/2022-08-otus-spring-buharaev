package ru.otus.homeWork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.exception.BookNotFoundException;
import ru.otus.homeWork.repositories.BookRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookRepository bookRepo;

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book save(Book book) {
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
        return bookRepo.save(book);
    }

    @Override
    public void delete(long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException());
        bookRepo.delete(book);
    }
}