package ru.otus.homeWork.dao;

import org.springframework.stereotype.Component;
import ru.otus.homeWork.domain.Question;
import ru.otus.homeWork.service.ReaderService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDaoCsv implements QuestionDao {

    private ReaderService csvReader;

    public QuestionDaoCsv(ReaderService csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public List<Question> getAllQuestions() {
        return toDto(csvReader.readData());
    }

    private List<Question> toDto(List<String[]> dataList) {
        return dataList.stream().map(qS -> toDto(qS)).collect(Collectors.toList());
    }

    private Question toDto(String[] data) {
        String[] answerOptions = new String[0];
        if(data.length > 3) {
                answerOptions = new String[data.length - 2];
                System.arraycopy(data, 2, answerOptions, 0, data.length - 2);
        }
        return Question.builder()
                        .id(Integer.valueOf(data[0]))
                        .text(data[1])
                        .correctAnswer(data[2])
                        .answerOptions(Arrays.asList(answerOptions))
                        .build();
    }
}