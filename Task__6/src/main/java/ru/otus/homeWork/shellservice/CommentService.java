package ru.otus.homeWork.shellservice;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homeWork.domain.Book;
import ru.otus.homeWork.domain.Comment;
import ru.otus.homeWork.repositories.BookRepository;
import ru.otus.homeWork.repositories.CommentRepository;

@ShellComponent
@ShellCommandGroup(value = "CRUD for comment")
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;

    private final BookRepository bookRepo;

    @Transactional
    @ShellMethod(value = "Save new comment", key = "c_save")
    public void save(String content, Long  book_id) {
        Book book = bookRepo.getById(book_id);
        if(book == null) {
            System.out.printf("Book with id=%d is not exists\n", book_id);
            return;
        }
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBook(book);
        commentRepo.save(comment);
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get comment by Id number", key = "c_get")
    public void getById(long id) {
        Comment comment = commentRepo.getById(id);
        if(comment == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(comment);
    }

    @Transactional
    @ShellMethod(value = "Delete comment by Id number", key = "c_delete")
    public void deleteById(long id) {
        commentRepo.deleteById(id);
        System.out.println("The comment is deleted");
    }
}
