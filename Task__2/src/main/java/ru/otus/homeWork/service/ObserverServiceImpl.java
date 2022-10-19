package ru.otus.homeWork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homeWork.repositories.QuestionDao;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ObserverServiceImpl implements ObserverService{

    private final AtomicInteger atomicInteger;

    private final QuestionDao dao;

    @Autowired
    public ObserverServiceImpl(QuestionDao dao) {
        this.dao = dao;
        this.atomicInteger = new AtomicInteger();
    }

    @Override
    public void checkAnswer(String correctAnswer) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if(userAnswer != null && !userAnswer.isEmpty() && correctAnswer.toLowerCase().equals(userAnswer.toLowerCase())) {
            atomicInteger.incrementAndGet();
        }
        System.out.println();
    }

    @Override
    public void getResult() {
        System.out.println("--------------------------------------------------------");
        System.out.println("Finish!");
        int correctAnswersCount = atomicInteger.intValue();
        int questionsCount = dao.getAllQuestions().size();
        System.out.printf("You scored %d out of %d.\n", correctAnswersCount, questionsCount);
        if(Double.valueOf(questionsCount)/2 <= correctAnswersCount) {
            System.out.println("Congratulations - you have completed test!");
        }
        else  {
            System.out.println("Sorry, but the test is not passed.");
        }
    }
}