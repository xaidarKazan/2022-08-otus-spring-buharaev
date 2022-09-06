package ru.otus.homeWork.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.ClassPathResource;
import ru.otus.homeWork.domain.TestQuestion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestQuestionDaoCSV implements TestQuestionDao {
    private final String csvPath;

    public TestQuestionDaoCSV(String csvPath) {
        this.csvPath = csvPath;
    }
    @Override
    public List<TestQuestion> getAll() {
        List<TestQuestion> allQuestions;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(csvPath);
        if (resource == null) {
            throw new IllegalArgumentException("CSV file not found!");
        } else {
            allQuestions = new ArrayList<>();
            try(FileReader fr = new FileReader(String.valueOf(resource));){

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try(FileReader fr = new FileReader(String.valueOf(resource));
                    CSVReader reader = new CSVReader(fr)) {
                String[] textLines;
                while ((textLines = reader.readNext()) != null) {
                    TestQuestion testQuestion = new TestQuestion();
                    /* some code */
                    allQuestions.add(testQuestion);
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
