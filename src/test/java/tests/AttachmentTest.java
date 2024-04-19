package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AttachmentTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    private static final String REPOSITORY = "ZhizhkunAV/test_allure";
    private static final int ISSUE = 80;

    @Test
    void testLambdaAttachmentsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());


        step("Открываем главную страницу", () -> {
            open("/");
            attachment("Source", webdriver().driver().source());
        });


    }

    @Test
    void stepAnotatedAttachmentsText() {
        WebSteps webSteps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());

        webSteps.openMainPage();
        webSteps.takeScreenshot();
    }
}

