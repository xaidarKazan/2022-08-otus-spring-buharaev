package ru.otus.homeWork.repositories;

import org.junit.Assert;
import org.junit.Test;
import ru.otus.homeWork.domain.Question;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.util.Arrays;

public class QuestionDaoCsvTest {
    @Test
    public void hasGetAllQuestionsCorrect() {
        String csvText = "Which season comes after summer?,autumn,winter,spring";

        Question testQuestion = new Question();

        try(BufferedReader br = new BufferedReader(new CharArrayReader(csvText.toCharArray()))) {
            String[] textLines;
            String line;
            while ((line = br.readLine()) != null) {
                textLines = line.split(",");

                testQuestion.setText(textLines[0]);
                testQuestion.setCorrectAnswer(textLines[1]);
                if(textLines.length > 2) {
                    String[] answerOptions = new String[textLines.length - 1];
                    System.arraycopy(textLines, 1, answerOptions, 0, textLines.length - 1);
                    testQuestion.setAnswerOptions(Arrays.asList(answerOptions));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] testOptions = {"autumn","winter","spring"};
        Assert.assertEquals("Which season comes after summer?", testQuestion.getText());
        Assert.assertEquals("autumn", testQuestion.getCorrectAnswer());
        Assert.assertArrayEquals(testOptions, testQuestion.getAnswerOptions().toArray());
    }
}
