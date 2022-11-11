package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.uiservice.BookUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class BookUIConsoleService implements BookUIService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getId() {
        System.out.println("Please enter book id");
        return scanner.nextLine();
    }

    @Override
    public String getName() {
        System.out.println("Please enter book title");
        return scanner.nextLine();
    }

    @Override
    public Integer getPublishingYear() {
        try {
            System.out.println("Please enter book publishing year");
            return Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
        }
        return null;
    }

    @Override
    public Book save() {
        Book book = new Book();
        book.setName(getName());
        book.setPublishingYear(getPublishingYear());
        return book;
    }

    @Override
    public boolean update(Book book) {
        if(book == null) {
            return false;
        }
        System.out.println("If not update title set empty and year is zero");
        String name = getName();
        Integer publishingYear = getPublishingYear();
        if(!name.isEmpty()) book.setName(name);
        if(publishingYear != null && publishingYear != 0) book.setPublishingYear(publishingYear);
        return true;
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
    public void isExists(Author author) {
        if(author == null) {
            System.out.println("Author is not exists.");
        }
    }

    @Override
    public void isExists(Genre genre) {
        if(genre == null) {
            System.out.println("Genre is not exists.");
        }
    }

    @Override
    public void print(Book book) {
        if(book == null)
            System.out.println("Wrong. Record is missing");
        else
            System.out.println(book);
    }

    @Override
    public void print(List<Book> books) {
        books.forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}