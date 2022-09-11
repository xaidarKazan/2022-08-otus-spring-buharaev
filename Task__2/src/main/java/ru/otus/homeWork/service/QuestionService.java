package ru.otus.homeWork.service;

import ru.otus.homeWork.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}