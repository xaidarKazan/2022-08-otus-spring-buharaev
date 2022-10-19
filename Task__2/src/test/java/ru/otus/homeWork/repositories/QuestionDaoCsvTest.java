package ru.otus.homeWork.repositories;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.otus.homeWork.domain.Question;
import ru.otus.homeWork.service.ReaderServiceCsvImpl;

import java.util.List;

public class QuestionDaoCsvTest {

    private final QuestionDao questionDaoCsv = new QuestionDaoCsv(new ReaderServiceCsvImpl("testData.csv"));
    @Test
    public void getAllQuestionsSize_isCorrect() {
        List<Question> allQuestions = questionDaoCsv.getAllQuestions();
        Assert.assertTrue(allQuestions.size() == 2);
    }
}