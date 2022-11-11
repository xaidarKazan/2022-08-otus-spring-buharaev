package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.uiservice.AuthorUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class AuthorUIConsoleService implements AuthorUIService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getFirstName() {
        System.out.println("Please enter author firstName");
        return scanner.nextLine();
    }

    @Override
    public String getLastName() {
        System.out.println("Please enter author lastName");
        return scanner.nextLine();
    }

    @Override
    public String getId() {
        System.out.println("Please enter author id");
        return scanner.nextLine();
    }

    @Override
    public Author save() {
        Author author = new Author();
        author.setFirstName(getFirstName());
        author.setLastName(getLastName());
        return author;
    }

    @Override
    public Author update(Author author) {
        if(author != null) {
            System.out.println("If not update set field is empty");
            String firstName = getFirstName(),
                   lastName = getLastName();
            if(!firstName.isEmpty()) author.setFirstName(firstName);
            if(!lastName.isEmpty()) author.setLastName(lastName);
        }
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
            System.out.println("Wrong. Record is missing");
        else
            System.out.println(author);
    }

    @Override
    public void print(List<Author> authors) {
        authors.forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}