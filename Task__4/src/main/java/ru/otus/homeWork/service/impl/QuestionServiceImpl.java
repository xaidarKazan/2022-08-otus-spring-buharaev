package ru.otus.homeWork.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.configuration.TestingAppProps;
import ru.otus.homeWork.dao.QuestionDao;
import ru.otus.homeWork.domain.Question;
import ru.otus.homeWork.service.ObserverService;
import ru.otus.homeWork.service.QuestionService;
import ru.otus.homeWork.service.TestingData;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final ObserverService observerService;

    private final TestingData testingData;

    private final MessageSource messageSource;

    private final TestingAppProps testingAppProps;

    public QuestionServiceImpl(QuestionDao dao, ObserverService observerService, TestingData testingData, MessageSource messageSource, TestingAppProps testingAppProps) {
        this.dao = dao;
        this.observerService = observerService;
        this.testingData = testingData;
        this.messageSource = messageSource;
        this.testingAppProps = testingAppProps;
    }

    @Override
    public void startingToTest() {
        List<Question> questionList = dao.getAllQuestions();
        for(Question question : questionList) {
            printQuestionAndOptions(question, testingAppProps.getLocale());
            boolean isAnswerCorrect = observerService.checkAnswer(question.getCorrectAnswer());
            testingData.scoreCounter(isAnswerCorrect);
        }
    }

    private void printQuestionAndOptions(Question question, Locale locale) {
        String questionLocalized;
        if(!locale.getLanguage().equals("en")) {
            questionLocalized = messageSource.getMessage("question." + question.getId(), null, locale);
        }
        else {
            questionLocalized = question.getText();
        }
        System.out.println(questionLocalized);
        List<String> answerOptions = question.getAnswerOptions();
        if(answerOptions != null) {
            Collections.shuffle(answerOptions);
            AtomicInteger i = new AtomicInteger();
            answerOptions.stream().forEach(option -> System.out.println(i.incrementAndGet() + ") " + option));
        }
    }
}