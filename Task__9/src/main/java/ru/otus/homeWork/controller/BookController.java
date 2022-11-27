package ru.otus.homeWork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.AuthorRepository;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.GenreRepository;

import java.util.Optional;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepo;

    private final AuthorRepository authorRepo;

    private final GenreRepository genreRepo;

    @GetMapping
    public String booksPage(Model model) {
        model.addAttribute("books", bookRepo.findAll());
        return "book/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "book/add";
    }

    @PostMapping("/add")
    public String saveNewBook(Optional<Book> bookOptional,
                              @RequestParam(value = "authorId") long authorId,
                              @RequestParam(value = "genreId") long genreId) {
        Book book = bookOptional.orElseThrow();
        Author author = authorRepo.findById(authorId).orElseThrow();
        Genre genre = genreRepo.findById(genreId).orElseThrow();
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepo.save(book);
        return "redirect:/book";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        Book book = bookRepo.findById(id).orElseThrow();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorRepo.findAll());
        model.addAttribute("genres", genreRepo.findAll());
        return "book/edit";
    }

    @PostMapping("/edit")
    public String saveEditBook(Book book,
                           @RequestParam(value = "authorId") long authorId,
                           @RequestParam(value = "genreId") long genreId) {
        Book editeBook = bookRepo.findById(book.getId()).orElseThrow();
        Author author = authorRepo.findById(authorId).orElseThrow();
        Genre genre = genreRepo.findById(genreId).orElseThrow();
        editeBook.setName(book.getName());
        editeBook.setPublishingYear(book.getPublishingYear());
        editeBook.setAuthor(author);
        editeBook.setGenre(genre);
        bookRepo.save(editeBook);
        return "redirect:/book";
    }

    @PostMapping("/remove")
    public String delete(@RequestParam(value = "id") long id) {
        Book book = bookRepo.findById(id).orElseThrow();
        bookRepo.delete(book);
        return "redirect:/book";
    }
}
