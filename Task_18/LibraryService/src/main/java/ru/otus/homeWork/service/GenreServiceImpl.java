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
import ru.otus.homeWork.domain.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService{

    private final RestTemplate rt;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            },
            fallbackMethod = "findAllGenres_Fallback"
    )
    @Override
    public ResponseEntity<List<Genre>> findAll() {
        ResponseEntity<List<Genre>> responseEntity = rt.exchange("/genres",
                                                                  HttpMethod.GET,
                                                                  null,
                                                                  new ParameterizedTypeReference<List<Genre>>() {}
                                                                );
        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            },
            fallbackMethod = "saveGenre_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class }
    )
    @Override
    public ResponseEntity<Genre> save(Genre genre, HttpMethod httpMethod) {
        ResponseEntity<Genre> responseEntity = rt.exchange("/genre",
                                                            httpMethod,
                                                            new HttpEntity(genre),
                                                            new ParameterizedTypeReference<Genre>() {}
                                                          );

        return responseEntity;
    }

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            },
            fallbackMethod = "genreDelete_Fallback",
            ignoreExceptions = { HttpClientErrorException.NotFound.class }
    )
    @Override
    public ResponseEntity delete(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        ResponseEntity<String> responseEntity = rt.exchange("/genre/{id}",
                                                             HttpMethod.DELETE,
                                                             null,
                                                             new ParameterizedTypeReference<String>() {},
                                                             params
                                                           );
        return responseEntity;
    }

    private ResponseEntity<List<Genre>> findAllGenres_Fallback() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity<Genre> saveGenre_Fallback(Genre genre, HttpMethod httpMethod) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    private ResponseEntity genreDelete_Fallback(long id) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }
}