package ru.otus.homeWork.shellservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.service.QuestionService;
import ru.otus.homeWork.service.TestingData;
import ru.otus.homeWork.service.UserProfileService;

@Service
public class TestApplicationService implements CommandLineRunner {

    private final UserProfileService userProfileService;

    private final QuestionService questionService;

    private final TestingData testingData;

    public TestApplicationService(UserProfileService userProfileService, QuestionService questionService, TestingData testingData) {
        this.userProfileService = userProfileService;
        this.questionService = questionService;
        this.testingData = testingData;
    }

    @Override
    public void run(String... args) throws Exception {
        userProfileService.setUserData();
        questionService.startingToTest();
        testingData.getResult();
    }
}