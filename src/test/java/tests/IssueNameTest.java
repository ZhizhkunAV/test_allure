package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class IssueNameTest {
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

        $(".Header-old ").$$("li").findBy(text("Solutions")).hover().$(".HeaderMenu-dropdown").$$("li").find(text("Enterprise")).click();
        $("#hero-section-brand-heading").shouldBe(text("The AI-powered\n" + "developer platform."));


    }

}
