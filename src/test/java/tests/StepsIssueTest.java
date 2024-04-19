package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsIssueTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    private static final String REPOSITORY = "ZhizhkunAV/test_allure";
    private static final String issueText = "TheFirstIssue";

    @Test
    void testLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        step("Открываем главную страницу", () -> {
            open("/");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").pressEnter();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Кликаем на Issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие текста Issue", () -> {
            $(By.linkText(issueText)).should(exist);
        });
    }

    @Test
    void stepAnotatedText() {
        WebSteps webSteps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        webSteps.openMainPage();
        webSteps.serchForRepository(REPOSITORY);
        webSteps.clickOnRepositoryLink(REPOSITORY);
        webSteps.openIssueTab();
        webSteps.shouldSeeIssueWithText(issueText);
    }
}
