package ru.otus.homeWork;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import ru.otus.homeWork.domain.TestQuestion;
import ru.otus.homeWork.service.TestQuestionService;

import java.util.List;

public class Main {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TestQuestionService service = context.getBean(TestQuestionService.class);
        List<TestQuestion> testQuestionList = service.getAllQuestions();


    }
}
