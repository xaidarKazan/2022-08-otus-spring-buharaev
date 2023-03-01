package ru.otus.homeWork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.exception.AuthorNotFoundException;
import ru.otus.homeWork.repositories.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;

    @Override
    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException());
    }

    @Override
    public Author save(Author author) {
        if(author.getId() != null) {
            Author editedAuthor = authorRepo.findById(author.getId()).orElseThrow(() -> new AuthorNotFoundException());
            editedAuthor.setFirstName(author.getFirstName());
            editedAuthor.setLastName(author.getLastName());
            author = editedAuthor;
        }
        return authorRepo.save(author);
    }

    @Override
    public void delete(long id) {
        Author author = authorRepo.findById(id).orElseThrow(() -> new AuthorNotFoundException());
        authorRepo.delete(author);
    }
}