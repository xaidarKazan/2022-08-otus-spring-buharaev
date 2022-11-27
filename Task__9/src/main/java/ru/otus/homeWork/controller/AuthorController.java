package ru.otus.homeWork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.homeWork.domain.Author;
import ru.otus.homeWork.repositories.AuthorRepository;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepo;

    @GetMapping
    public String authorsPage(Model model) {
            model.addAttribute("authors", authorRepo.findAll());
        return "author/list";
    }

    @PostMapping("/add")
    public String saveNewAuthor(Author author) {
        authorRepo.save(author);
        return "redirect:/author";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        Author author = authorRepo.findById(id).orElseThrow();
        model.addAttribute("author", author);
        return "author/edit";
    }

    @PostMapping("/edit")
    public String saveEditAuthor(Author author) {
        Author servedAuthor = authorRepo.findById(author.getId()).orElseThrow();
        servedAuthor.setFirstName(author.getFirstName());
        servedAuthor.setLastName(author.getLastName());
        authorRepo.save(servedAuthor);
        return "redirect:/author";
    }

    @PostMapping("/remove")
    public String delete(@RequestParam(value = "id") long id) {
        Author author = authorRepo.findById(id).orElseThrow();
        authorRepo.delete(author);
        return "redirect:/author";
    }
}