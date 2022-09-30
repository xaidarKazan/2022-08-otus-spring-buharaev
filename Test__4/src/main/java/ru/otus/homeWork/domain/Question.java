package ru.otus.homeWork.domain;

import lombok.Builder;

import java.util.List;

@Builder
public class Question {

    private int id;
    private String text;
    private String correctAnswer;
    private List<String> answerOptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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