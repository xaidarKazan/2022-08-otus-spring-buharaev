package ru.otus.homeWork.dao;

import ru.otus.homeWork.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}