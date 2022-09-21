package ru.otus.homeWork.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homeWork.service.ObserverService;

import java.util.Scanner;

@Service
public class ObserverServiceImpl implements ObserverService {

    @Override
    public boolean checkAnswer(String correctAnswer) {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        if(userAnswer != null && !userAnswer.isEmpty() && correctAnswer.toLowerCase().equals(userAnswer.toLowerCase())) {
            return true;
        }
        return false;
    }
}