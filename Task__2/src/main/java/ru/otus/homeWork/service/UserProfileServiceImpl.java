package ru.otus.homeWork.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class UserProfileServiceImpl implements UserProfileService{

    @Override
    public void setUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, this is TestService. Let's start an acquaintance.");
        System.out.println("What is your surname?");
        String userSurname = scanner.nextLine();
        System.out.println("What is your name?");
        String userName = scanner.nextLine();
        System.out.println();
    }
}