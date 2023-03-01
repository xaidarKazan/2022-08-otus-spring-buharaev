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
import ru.otus.homeWork.domain.Author;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final RestTemplate rt;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllAuthors_Fallback"
    )
    @Override
    public ResponseEntity<List<Author>> findAll() {
        ResponseEntity<List<Author>> responseEntity = rt.exchange("/authors",
                                                                    HttpMethod.GET,
                                                                    null,
                                                                    new ParameterizedTypeReference<List<Author>>() {}
                                                            );
        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveAuthor_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class}
    )
    @Override
    public ResponseEntity<Author> save(Author author, HttpMethod httpMethod) {
        ResponseEntity<Author> responseEntity = rt.exchange("/author",
                                                            httpMethod,
                                                            new HttpEntity(author),
                                                            new ParameterizedTypeReference<Author>() {}
                                                    );

        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "authorDelete_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class}
    )
    @Override
    public ResponseEntity delete(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        ResponseEntity<String> responseEntity = rt.exchange("/author/{id}",
                                                            HttpMethod.DELETE,
                                                            null,
                                                            new ParameterizedTypeReference<String>() {},
                                                            params
                                                    );
        return responseEntity;
    }

    private ResponseEntity<List<Author>> findAllAuthors_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity<Author> saveAuthor_Fallback(Author author, HttpMethod httpMethod) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity authorDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}