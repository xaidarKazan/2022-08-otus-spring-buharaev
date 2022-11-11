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
import ru.otus.homeWork.uiservice.BookUIService;
import ru.otus.homeWork.uiservice.CommentUIService;

import java.util.List;

@ShellComponent
@ShellCommandGroup(value = "CRUD for comment")
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepo;

    private final BookRepository bookRepo;

    private final CommentUIService commentUIService;

    private final BookUIService bookUIService;

    @Transactional
    @ShellMethod(value = "Save new comment", key = "c_save")
    public void save() {
        boolean isSaved = false;
        Comment comment = commentUIService.save();

        String bookId = bookUIService.getId();
        Book book = bookRepo.findById(bookId).orElse(null);
        commentUIService.isExists(book);

        if(book != null) {
            comment.setBook(book);
            comment = commentRepo.save(comment);

            book.getComments().add(comment);
            bookRepo.save(book);

            isSaved = true;
        }
        commentUIService.isSaved(isSaved);
    }

    @ShellMethod(value = "Get comment by Id number", key = "c_getById")
    public void getById() {
        String id = commentUIService.getId();
        Comment comment = commentRepo.findById(id).orElse(null);
        commentUIService.print(comment);
    }

    @Transactional
    @ShellMethod(value = "Delete comment by Id number", key = "c_delete")
    public void deleteById() {
        String id = commentUIService.getId();
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment != null) {
            commentRepo.delete(comment);
        }
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get all comments by book id", key = "c_getCommentsByBookId")
    public void getAllComments() {
        String bookId = bookUIService.getId();
        Book book = bookRepo.findById(bookId).orElse(null);
        commentUIService.isExists(book);
        if(book != null) {
            List<Comment> comments = commentRepo.findByBookId(bookId);
            commentUIService.print(comments);
        }
    }
}