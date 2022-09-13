package ru.otus.homeWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestApplicationServiceImpl implements TestApplicationService {

    private final UserProfileService userProfileService;

    private final QuestionService questionService;

    private final ObserverService observerService;

    @Autowired
    public TestApplicationServiceImpl(UserProfileService userProfileService, QuestionService questionService, ObserverService observerService) {
        this.userProfileService = userProfileService;
        this.questionService = questionService;
        this.observerService = observerService;
    }

    public void run() {
        userProfileService.setUserData();
        questionService.startingToTest();
        observerService.getResult();
    }
}