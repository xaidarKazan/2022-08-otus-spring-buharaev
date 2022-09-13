package ru.otus.homeWork.domain;

import java.util.ArrayList;
import java.util.List;


public class Question {

    private String text;
    private String correctAnswer;
    private List<String> answerOptions = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrectAnswer() {
        int correctAnswerPosition = answerOptions.indexOf(correctAnswer);
        return correctAnswerPosition == -1 ? correctAnswer : String.valueOf(correctAnswerPosition + 1);
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
}