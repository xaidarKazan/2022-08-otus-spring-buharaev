package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.uiservice.BookUIService;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;

import java.util.List;
import java.util.Scanner;

@Component
public class BookUIConsoleService implements BookUIService {
    @Override
    public Long getId() {
        Long id = null;
        System.out.println("Please enter id value");
        Scanner scanner = new Scanner(System.in);
        try {
            id = Long.parseLong(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
        }
        return id;
    }

    @Override
    public Book save() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter book title");
            String name = scanner.nextLine();
            System.out.println("Please enter book publishing year");
            Integer publishingYear = Integer.parseInt(scanner.nextLine());
            System.out.println("Please enter book author id");
            Author author = new Author();
            author.setId(Long.parseLong(scanner.nextLine()));
            System.out.println("Please enter book genre id");
            Genre genre = new Genre();
            genre.setId(Long.parseLong(scanner.nextLine()));
            Book book = new Book();
            book.setName(name);
            book.setPublishingYear(publishingYear);
            book.setAuthor(author);
            book.setGenre(genre);
            return book;
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
        }
        return null;
    }

    @Override
    public boolean update(Book book) {
        if(book == null) {
            return false;
        }
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter book title if you want to change or else empty enter");
            String name = scanner.nextLine();
            System.out.println("Please enter book publishing year if you want to change or else enter zero");
            Integer publishingYear = Integer.parseInt(scanner.nextLine());
            System.out.println("Please enter book author id if you want to change or else enter zero");
            Author author = new Author();
            author.setId(Long.parseLong(scanner.nextLine()));
            System.out.println("Please enter book genre id if you want to change or else enter zero");
            Genre genre = new Genre();
            genre.setId(Long.parseLong(scanner.nextLine()));
            if(!name.isEmpty()) book.setName(name);
            if(publishingYear != 0) book.setPublishingYear(publishingYear);
            if(author.getId() != 0) book.setAuthor(author);
            if(genre.getId() != 0) book.setGenre(genre);
            return true;
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
            System.out.println("Book is not saved");
            System.out.println("Please, choose command");
        }
        return false;
    }

    @Override
    public void isSaved(boolean isSaved) {
        if(!isSaved) {
            System.out.println("Book is not saved");
            System.out.println("Please, choose command");
        }
        else {
            System.out.println("Book is saved");
        }
    }

    @Override
    public void isExists(Author author, Long id) {
        if(author == null) {
            System.out.printf("Author with id=%d is not exists. \n", id);
        }
    }

    @Override
    public void isExists(Genre genre, Long id) {
        if(genre == null) {
            System.out.printf("Genre with id=%d is not exists. \n", id);
        }
    }

    @Override
    public void print(Book book) {
        if(book == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(book);
    }

    @Override
    public void print(List<Book> books) {
        books.stream().forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}