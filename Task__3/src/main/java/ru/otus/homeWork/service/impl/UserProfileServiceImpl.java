package ru.otus.homeWork.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.configuration.TestingAppProps;
import ru.otus.homeWork.service.UserProfileService;

import java.util.Scanner;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final TestingAppProps testingAppProps;

    private final MessageSource messageSource;

    public UserProfileServiceImpl(TestingAppProps testingAppProps, MessageSource messageSource) {
        this.testingAppProps = testingAppProps;
        this.messageSource = messageSource;
    }

    @Override
    public void setUserData() {
        String questionLocalized;
        Scanner scanner = new Scanner(System.in);
        questionLocalized = messageSource.getMessage("user.greeting", null, testingAppProps.getLocale());
        System.out.println(questionLocalized);
        questionLocalized = messageSource.getMessage("user.surname", null, testingAppProps.getLocale());
        System.out.println(questionLocalized);
        String userSurname = scanner.nextLine();
        questionLocalized = messageSource.getMessage("user.name", null, testingAppProps.getLocale());
        System.out.println(questionLocalized);
        String userName = scanner.nextLine();
        System.out.println();
    }
}