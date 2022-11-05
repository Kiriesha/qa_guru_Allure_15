package qa.guru;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {

    private static final String REPOSITORY = "Kiriesha/qa_guru_Allure_15";
    private static final int ISSUE = 80;

    @Test
    public void stepLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Найти репозиторий" + REPOSITORY,() ->{
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Кликаем по ссылке репозитория", () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб issue", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие issue с номером" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
}
