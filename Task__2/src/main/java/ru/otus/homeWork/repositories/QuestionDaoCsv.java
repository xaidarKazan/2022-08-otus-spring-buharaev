package ru.otus.homeWork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Question;
import ru.otus.homeWork.service.ReaderService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionDaoCsv implements QuestionDao {

    private ReaderService csvReader;

    @Autowired
    public QuestionDaoCsv(ReaderService csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public List<Question> getAllQuestions() {
        ArrayList<Question> allQuestions = new ArrayList<>();
        List<String[]> csvRawData = csvReader.readData();
        for(String[] data : csvRawData) {
            Question question = new Question();
            question.setText(data[0]);
            question.setCorrectAnswer(data[1]);
            if(data.length > 2) {
                String[] answerOptions = new String[data.length - 1];
                System.arraycopy(data, 1, answerOptions, 0, data.length - 1);
                question.setAnswerOptions(Arrays.asList(answerOptions));
            }
            allQuestions.add(question);
        }
        return allQuestions;
    }
}