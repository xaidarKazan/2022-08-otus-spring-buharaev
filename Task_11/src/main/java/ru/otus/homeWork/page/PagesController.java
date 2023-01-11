package ru.otus.homeWork.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("authors")
    public String authorsPage() {
        return "authorsPage";
    }

    @GetMapping("genres")
    public String genresPage() {
        return "genresPage";
    }

    @GetMapping("books")
    public String booksPage() {
        return "booksPage";
    }
}