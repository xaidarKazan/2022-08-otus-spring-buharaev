package ru.otus.homeWork.uiservice.console;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Genre;
import ru.otus.homeWork.uiservice.GenreUIService;

import java.util.List;
import java.util.Scanner;

@Component
public class GenreUIConsoleService implements GenreUIService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String getId() {
        System.out.println("Please enter genre id");
        return scanner.nextLine();
    }

    @Override
    public String getName() {
        System.out.println("Please enter genre name");
        return scanner.nextLine();
    }

    @Override
    public Genre save() {
        Genre genre = new Genre();
        genre.setName(getName());
        return genre;
    }

    @Override
    public Genre update(Genre genre) {
        if(genre != null) {
            System.out.println("If not update set field is empty");
            String name = getName();
            if(!name.isEmpty()) genre.setName(name);
        }
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
            System.out.println("Wrong. Record is missing");
        else
            System.out.println(genre);
    }

    @Override
    public void print(List<Genre> genres) {
        genres.forEach(System.out::println);
    }

    @Override
    public void print(long count) {
        System.out.println(count);
    }
}