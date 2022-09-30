package ru.otus.homeWork.shellservice;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homeWork.service.QuestionService;
import ru.otus.homeWork.service.TestingData;
import ru.otus.homeWork.service.UserProfileService;

@ShellComponent
public class TestApplicationService /*implements CommandLineRunner*/ {

    private final UserProfileService userProfileService;

    private final QuestionService questionService;

    private final TestingData testingData;

    private ApplicationStage testingStage;

    public TestApplicationService(UserProfileService userProfileService, QuestionService questionService, TestingData testingData) {
        this.userProfileService = userProfileService;
        this.questionService = questionService;
        this.testingData = testingData;
        this.testingStage = ApplicationStage.LOGOUT;
    }

    @ShellMethod(value = "Login", key = "login")
    @ShellMethodAvailability(value = "isLoginCommandAvailable")
    public void login() {
        userProfileService.setUserData();
        testingStage = ApplicationStage.LOGGED;
    }

    private Availability isLoginCommandAvailable() {
        return testingStage != ApplicationStage.LOGOUT ? Availability.unavailable("Вы зарегестрированы.") : Availability.available();
    }

    @ShellMethod(value = "To start testing", key = {"test", "testing"})
    @ShellMethodAvailability(value = "isStartTestCommandAvailable")
    public void startTest() {
        questionService.startingToTest();
        testingStage = ApplicationStage.TESTED;
    }

    private Availability isStartTestCommandAvailable() {
        return testingStage == ApplicationStage.TESTED ? Availability.unavailable("Вы уже прошли тестирование!") :
                testingStage == ApplicationStage.LOGOUT ? Availability.unavailable("Сначала залогиньтесь") : Availability.available();
    }

    @ShellMethod(value = "View results", key = {"result", "results"})
    @ShellMethodAvailability(value = "isGetResultCommandAvailable")
    public void getResult() {
        testingData.getResult();
    }

    private Availability isGetResultCommandAvailable() {
        return testingStage == ApplicationStage.TESTED ? Availability.available() :
                testingStage == ApplicationStage.LOGOUT ? Availability.unavailable("Сначала залогиньтесь") : Availability.unavailable("Вам необходимо пройти тест.");
    }

    @ShellMethod(value = "Logout", key = {"out", "logout"})
    public void logout() {
        testingStage = ApplicationStage.LOGOUT;
    }
}