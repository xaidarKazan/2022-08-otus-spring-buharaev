package ru.otus.homeWork.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.exception.AuthorNotFoundException;
import ru.otus.homeWork.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepo;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllAuthors_Fallback"
    )
    @Override
    public ResponseEntity<List<Author>> findAll() {
        return ResponseEntity.ok(authorRepo.findAll());
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException());
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveAuthor_Fallback",
            ignoreExceptions = { AuthorNotFoundException.class }
    )
    @Override
    public ResponseEntity<Author> save(Author author) {
        if(author.getId() != null) {
            Author editedAuthor = authorRepo.findById(author.getId()).orElseThrow(() -> new AuthorNotFoundException());
            editedAuthor.setFirstName(author.getFirstName());
            editedAuthor.setLastName(author.getLastName());
            author = editedAuthor;
        }
        return ResponseEntity.ok(authorRepo.save(author));
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "authorDelete_Fallback",
            ignoreExceptions = { AuthorNotFoundException.class }
    )
    @Override
    public ResponseEntity delete(long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException());
        authorRepo.delete(author);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity<List<Author>> findAllAuthors_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private  ResponseEntity<Author> saveAuthor_Fallback(Author author) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity authorDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}