package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.uiservice.GenreUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class GenreUIConsoleService implements GenreUIService {

    @Override
    public Long getId() {
        Long id = null;
        System.out.println("Please enter id value");
        Scanner scanner = new Scanner(System.in);
        try {
            id = Long.parseLong(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("The value is not number");
            System.out.println("Please, choose command");
        }
        return id;
    }

    @Override
    public Genre save() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter genre name");
        String name = scanner.nextLine();
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    @Override
    public void isSaved(boolean isSaved) {
        if(!isSaved) {
            System.out.println("Genre is not saved");
            System.out.println("Please, choose command");
        }
        else {
            System.out.println("Genre is saved");
        }
    }

    @Override
    public void print(Genre genre) {
        if(genre == null)
            System.out.println("Wrong id. Record is missing");
        else
            System.out.println(genre);
    }

    @Override
    public void print(List<Genre> genres) {
        genres.stream().forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}