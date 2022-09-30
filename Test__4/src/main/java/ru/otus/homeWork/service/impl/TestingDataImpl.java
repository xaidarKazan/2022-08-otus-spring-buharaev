package ru.otus.homeWork.service.impl;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.configuration.TestingAppProps;
import ru.otus.homeWork.dao.QuestionDao;
import ru.otus.homeWork.service.TestingData;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TestingDataImpl implements TestingData {
    private final AtomicInteger atomicInteger;

    private final QuestionDao dao;

    private final MessageSource messageSource;

    private final TestingAppProps testingAppProps;

    public TestingDataImpl(QuestionDao dao, MessageSource messageSource, TestingAppProps testingAppProps) {
        this.dao = dao;
        this.messageSource = messageSource;
        this.testingAppProps = testingAppProps;
        this.atomicInteger = new AtomicInteger();
    }

    @Override
    public void scoreCounter(boolean hasIncrement) {
        if(hasIncrement) {
            atomicInteger.incrementAndGet();
        }
    }

    @Override
    public void getResult() {
        System.out.println("--------------------------------------------------------");
        String questionLocalized = messageSource.getMessage("test.finish", null, testingAppProps.getLocale());
        System.out.println(questionLocalized);
        int correctAnswersCount = atomicInteger.intValue();
        int questionsCount = dao.getAllQuestions().size();
        questionLocalized = messageSource.getMessage("test.info", new Object[]{Integer.valueOf(correctAnswersCount), Integer.valueOf(questionsCount)}, testingAppProps.getLocale());
        System.out.printf(questionLocalized);
        System.out.println();
        if(Double.valueOf(questionsCount)/2 <= correctAnswersCount) {
            questionLocalized = messageSource.getMessage("test.result.good", null, testingAppProps.getLocale());
        }
        else  {
            questionLocalized = messageSource.getMessage("test.result.bad", null, testingAppProps.getLocale());
        }
        System.out.println(questionLocalized);
    }
}