package ru.otus.homeWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.dao.QuestionDao;
import ru.otus.homeWork.domain.Question;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    private final ObserverService observerService;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao, ObserverService observerService) {
        this.dao = dao;
        this.observerService = observerService;
    }

    @Override
    public void startingToTest() {
        List<Question> questionList = dao.getAllQuestions();
        for(Question tq : questionList) {
            printQuestionAndOptions(tq);
            observerService.checkAnswer(tq.getCorrectAnswer());
        }
    }

    public void printQuestionAndOptions(Question question) {
        System.out.println(question.getText());
        List<String> answerOptions = question.getAnswerOptions();
        if(answerOptions != null) {
            Collections.shuffle(answerOptions);
            AtomicInteger i = new AtomicInteger();
            answerOptions.stream().forEach(option -> System.out.println(i.incrementAndGet() + ") " + option));
        }
    }
}