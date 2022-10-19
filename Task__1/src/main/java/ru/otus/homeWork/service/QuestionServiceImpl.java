package ru.otus.homeWork.service;

import ru.otus.homeWork.repositories.QuestionDao;
import ru.otus.homeWork.domain.Question;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return dao.getAll();
    }
}