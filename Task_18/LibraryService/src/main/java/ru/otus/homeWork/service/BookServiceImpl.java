package ru.otus.homeWork.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.homeWork.domain.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{

    private final RestTemplate rt;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllBooks_Fallback"
    )
    @Override
    public ResponseEntity<List<Book>> findAll() {
        ResponseEntity<List<Book>> responseEntity = rt.exchange("/books",
                                                                 HttpMethod.GET,
                                                                 null,
                                                                 new ParameterizedTypeReference<List<Book>>() {}
                                                               );
        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveBook_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class }
    )
    @Override
    public ResponseEntity<Book> save(Book book, HttpMethod httpMethod) {
        ResponseEntity<Book> responseEntity = rt.exchange("/book",
                httpMethod,
                new HttpEntity(book),
                new ParameterizedTypeReference<Book>() {}
        );

        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "bookDelete_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class }
    )
    @Override
    public ResponseEntity delete(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        ResponseEntity<String> responseEntity = rt.exchange("/book/{id}",
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<String>() {},
                params
        );
        return responseEntity;
    }

    private ResponseEntity<List<Book>> findAllBooks_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity<Book> saveBook_Fallback(Book book, HttpMethod httpMethod) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity bookDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}