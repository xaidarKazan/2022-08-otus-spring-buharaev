package ru.otus.homeWork.domain;

import lombok.Data;

import java.util.List;


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
}
