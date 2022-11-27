package ru.otus.homeWork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.repositories.GenreRepository;

@Controller
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreRepository genreRepo;

    @GetMapping
    public String genresPage(Model model) {
        model.addAttribute("genres", genreRepo.findAll());
        return "genre/list";
    }

    @PostMapping("/add")
    public String saveNewGenre(Genre genre) {
        genreRepo.save(genre);
        return "redirect:/genre";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam(value = "id") long id, Model model) {
        Genre genre = genreRepo.findById(id).orElseThrow();
        model.addAttribute("genre", genre);
        return "genre/edit";
    }

    @PostMapping("/edit")
    public String saveEditGenre(Genre genre) {
        Genre savedGenre = genreRepo.findById(genre.getId()).orElseThrow();
        savedGenre.setName(genre.getName());
        genreRepo.save(savedGenre);
        return "redirect:/genre";
    }

    @PostMapping("/remove")
    public String delete(@RequestParam("id") long id) {
        Genre genre = genreRepo.findById(id).orElseThrow();
        genreRepo.delete(genre);
        return "redirect:/genre";
    }
}