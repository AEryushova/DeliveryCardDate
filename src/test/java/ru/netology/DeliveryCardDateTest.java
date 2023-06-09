package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.utils.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardDateTest {
    @BeforeAll
    static void setUpAllAllure(){
        SelenideLogger.addListener("allure",new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }


    @BeforeEach
    void setUpAll() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @Test
    void shouldMakeAnAppointmentHappyPath() {
        int numberDaysFromCurrentDate = 3;
        String firstMeetingDay = DataGenerator.generateDate(numberDaysFromCurrentDate);
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(firstMeetingDay);
        form.$("[data-test-id='name'] input").setValue(DataGenerator.generateName());
        form.$("[data-test-id='phone'] input").setValue(DataGenerator.generateNumber());
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDay));
    }

    @Test
    void shouldReschedulTheMeetingHappyPath() {
        int numberDaysFromCurrentDateMin = 3;
        int numberDaysFromCurrentDateMax = 8;
        String firstMeetingDay = DataGenerator.generateDate(numberDaysFromCurrentDateMin);
        String secondMeetingDay = DataGenerator.generateDate(numberDaysFromCurrentDateMax);
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue(DataGenerator.generateCity());
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(firstMeetingDay);
        form.$("[data-test-id='name'] input").setValue(DataGenerator.generateName());
        form.$("[data-test-id='phone'] input").setValue(DataGenerator.generateNumber());
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + firstMeetingDay));
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        form.$("[data-test-id='date'] input").setValue(secondMeetingDay);
        form.$(".button__content").click();
        $("[data-test-id='replan-notification']").shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать? "));
        $x("//span[text()='Перепланировать']").click();
        $("[data-test-id='success-notification']").shouldHave(Condition.text("Встреча успешно запланирована на " + secondMeetingDay));
    }
}



