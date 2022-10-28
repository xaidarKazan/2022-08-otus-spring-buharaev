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
        if(comment == null) {
            commentUIService.isSaved(false);
            return;
        }

        Long bookId = comment.getBook().getId();
        Book book = bookRepo.getById(bookId);
        commentUIService.isExists(book, bookId);

        if(book != null) {
            comment.setBook(book);
            commentRepo.save(comment);
            isSaved = true;
        }
        commentUIService.isSaved(isSaved);
    }

    @ShellMethod(value = "Get comment by Id number", key = "c_get")
    public void getById() {
        Long id = commentUIService.getId();
        if(id != null) {
            Comment comment = commentRepo.getById(id);
            commentUIService.print(comment);
        }
    }

    @Transactional
    @ShellMethod(value = "Delete comment by Id number", key = "c_delete")
    public void deleteById() {
        Long id = commentUIService.getId();
        if(id != null) {
            commentRepo.deleteById(id);
        }
    }

    @Transactional(readOnly = true)
    @ShellMethod(value = "Get book comments", key = "c_getBookComments")
    public void getAllComments() {
        Long id = commentUIService.getId();
        if(id != null) {
            Book book = bookRepo.getById(id);
            commentUIService.print(book.getComments());
        }
    }
}