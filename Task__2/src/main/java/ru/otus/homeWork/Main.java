package ru.otus.homeWork;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.homeWork.service.TestApplicationService;

@ComponentScan
public class Main {
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        TestApplicationService testApp = context.getBean(TestApplicationService.class);
        testApp.run();
    }
}