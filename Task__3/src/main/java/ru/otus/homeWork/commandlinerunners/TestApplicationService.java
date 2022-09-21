package ru.otus.homeWork.commandlinerunners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.configuration.TestingAppProps;
import ru.otus.homeWork.service.QuestionService;
import ru.otus.homeWork.service.TestingData;
import ru.otus.homeWork.service.UserProfileService;

@Service
public class TestApplicationService implements CommandLineRunner {

    private final UserProfileService userProfileService;

    private final QuestionService questionService;

    private final TestingData testingData;

    private final TestingAppProps testingAppProps;

    public TestApplicationService(UserProfileService userProfileService, QuestionService questionService, TestingData testingData, TestingAppProps testingAppProps) {
        this.userProfileService = userProfileService;
        this.questionService = questionService;
        this.testingData = testingData;
        this.testingAppProps = testingAppProps;
    }

    @Override
    public void run(String... args) throws Exception {
        userProfileService.setUserData(testingAppProps.getLocale());
        questionService.startingToTest(testingAppProps.getLocale());
        testingData.getResult(testingAppProps.getLocale());
    }
}