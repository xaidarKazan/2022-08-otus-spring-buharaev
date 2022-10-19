package ru.otus.homeWork.repositories;

import ru.otus.homeWork.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}
