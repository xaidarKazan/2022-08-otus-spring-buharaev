package ru.otus.homeWork.domain;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class TestQuestion {

    private String text;
    private String correctAnswer;
    private List<String> answerOptions;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<String> answerOptions) {
        this.answerOptions = answerOptions;
    }

    public void println() {
        System.out.println(getText());
        if(answerOptions != null) {
            Collections.shuffle(answerOptions);
            AtomicInteger i = new AtomicInteger();
            answerOptions.stream().forEach(option -> System.out.println(i.incrementAndGet() + ") " + option));
        }
        System.out.println();
    }
}
