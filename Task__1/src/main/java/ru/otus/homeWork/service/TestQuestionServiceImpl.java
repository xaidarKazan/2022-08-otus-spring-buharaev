package ru.otus.homeWork.service;

import ru.otus.homeWork.dao.TestQuestionDao;
import ru.otus.homeWork.domain.TestQuestion;

import java.util.List;

public class TestQuestionServiceImpl implements TestQuestionService{

    private TestQuestionDao dao;

    public TestQuestionServiceImpl(TestQuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<TestQuestion> getAllQuestions() {
        return dao.getAll();
    }
}
