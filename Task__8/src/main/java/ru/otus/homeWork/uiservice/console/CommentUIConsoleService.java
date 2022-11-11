package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Comment;
import ru.otus.homeWork.uiservice.CommentUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class CommentUIConsoleService implements CommentUIService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getId() {
        System.out.println("Please enter id value");
        return scanner.nextLine();
    }

    @Override
    public String getContent() {
        System.out.println("Please enter content of comment");
        return scanner.nextLine();
    }

    @Override
    public Comment save() {
        Comment comment = new Comment();
        comment.setContent(getContent());
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
    public void isExists(Book book) {
        if(book == null) {
            System.out.println("Book is not exists.");
        }
    }

    @Override
    public void print(Comment comment) {
        if(comment == null)
            System.out.println("Wrong. Record is missing");
        else
            System.out.println(comment);
    }

    @Override
    public void print(List<Comment> comments) {
        comments.forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}