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
import static org.openqa.selenium.By.linkText;


public class IssueNameTest {
    private static final String issueText = "TheFirstIssue";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void testHaveText() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/");
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("ZhizhkunAV/test_allure");
        $("#query-builder-test").pressEnter();
        $(linkText("ZhizhkunAV/test_allure")).click();
        $("#issues-tab").click();
        $(By.linkText(issueText)).should(exist);
    }
}
