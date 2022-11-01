package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Comment;
import ru.otus.homeWork.uiservice.CommentUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class CommentUIConsoleService implements CommentUIService {
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
    public Comment save() {
        Comment comment = null;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter comment content");
            String content = scanner.nextLine();
            System.out.println("Please enter book id");
            Book book = new Book();
            book.setId(Long.parseLong(scanner.nextLine()));
            comment = new Comment();
            comment.setContent(content);
            comment.setBook(book);
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
        }
        return comment;
    }

    @Override
    public void isSaved(boolean isSaved) {
        if(!isSaved) {
            System.out.println("Comment is not saved");
            System.out.println("Please, choose command");
        }
        else {
            System.out.println("Comment is saved");
        }
    }

    @Override
    public void isExists(Book book, Long id) {
        if(book == null) {
            System.out.printf("Book with id=%d is not exists. \n", id);
        }
    }

    @Override
    public void print(Comment comment) {
        if(comment == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(comment);
    }

    @Override
    public void print(List<Comment> comments) {
        comments.stream().forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}