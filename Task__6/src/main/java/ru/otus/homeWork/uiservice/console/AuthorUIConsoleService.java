package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.uiservice.AuthorUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class AuthorUIConsoleService implements AuthorUIService {

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
            System.out.println("Please, choose command");
        }
        return id;
    }

    @Override
    public Author save() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter author firstName");
        String firstName = scanner.nextLine();
        System.out.println("Please enter author lastName");
        String lastName = scanner.nextLine();
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return author;
    }

    @Override
    public void isSaved(boolean isSaved) {
        if(!isSaved) {
            System.out.println("Author is not saved");
            System.out.println("Please, choose command");
        }
        else {
            System.out.println("Author is saved");
        }
    }

    @Override
    public void print(Author author) {
        if(author == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(author);
    }

    @Override
    public void print(List<Author> authors) {
        authors.stream().forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}