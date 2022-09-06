package ru.otus.homeWork.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import ru.otus.homeWork.domain.Question;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao {
    private final String csvPath;

    public QuestionDaoCsv(String csvPath) {
        this.csvPath = csvPath;
    }
    @Override
    public List<Question> getAll() {
        Resource resource = new ClassPathResource(csvPath);
        ArrayList<Question> allQuestions;
        if (resource == null) {
            throw new IllegalArgumentException("CSV file not found!");
        } else {
            allQuestions = new ArrayList<>();
            try(FileReader fr = new FileReader(resource.getFile());
                CSVReader reader = new CSVReader(fr)) {
                String[] textLines;
                while ((textLines = reader.readNext()) != null) {
                    Question question = new Question();
                    question.setText(textLines[0]);
                    question.setCorrectAnswer(textLines[1]);
                    if(textLines.length > 2) {
                        String[] answerOptions = new String[textLines.length - 1];
                        System.arraycopy(textLines, 1, answerOptions, 0, textLines.length - 1);
                        question.setAnswerOptions(Arrays.asList(answerOptions));
                    }
                    allQuestions.add(question);
                }
            }  catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CsvValidationException e) {
                throw new RuntimeException(e);
            }
        }
        return allQuestions;
    }
}
