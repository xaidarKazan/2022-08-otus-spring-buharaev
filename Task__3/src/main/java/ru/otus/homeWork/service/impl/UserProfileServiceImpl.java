package ru.otus.homeWork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.service.UserProfileService;

import java.util.Locale;
import java.util.Scanner;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private final MessageSource messageSource;

    @Autowired
    public UserProfileServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void setUserData(Locale locale) {
        String questionLocalized;
        Scanner scanner = new Scanner(System.in);
        questionLocalized = messageSource.getMessage("user.greeting", null, locale);
        System.out.println(questionLocalized);
        questionLocalized = messageSource.getMessage("user.surname", null, locale);
        System.out.println(questionLocalized);
        String userSurname = scanner.nextLine();
        questionLocalized = messageSource.getMessage("user.name", null, locale);
        System.out.println(questionLocalized);
        String userName = scanner.nextLine();
        System.out.println();
    }
}