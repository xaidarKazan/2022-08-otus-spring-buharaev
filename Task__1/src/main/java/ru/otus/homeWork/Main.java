package ru.otus.homeWork;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homeWork.domain.Question;
import ru.otus.homeWork.service.QuestionService;

import java.util.List;

public class Main {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService service = context.getBean(QuestionService.class);
        List<Question> questionList = service.getAllQuestions();
        for(Question tq : questionList) {
            tq.println();
        }
    }
}
