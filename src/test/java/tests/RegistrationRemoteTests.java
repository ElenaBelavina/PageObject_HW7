package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationRemoteTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
//        Configuration.holdBrowserOpen = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        Configuration.browserCapabilities = capabilities;
//
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
//    void afterEach() {
//        Selenide.closeWebDriver();
//    }
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//        Attach.browserConsoleLogs();
//        Attach.addVideo();

        Selenide.closeWebDriver();
    }

    @Test
    @Feature("Проверка формы регистрации")
    @Story("Заполнение полей формы регистрации")
    @Owner("Elena Belavina")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тест на заполнение полей формы регистрации")
    @Tag("demoqa")
    void successfulRegistrationTest() {
        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
            SelenideElement bannerRoot = $(".fc-consent-root");
            if (bannerRoot.isDisplayed()) {
                bannerRoot.$(byText("Consent")).click();
            }
        });
        step("Заполняем поля формы", () -> {
            $("#firstName").setValue("Alex");
            $("#lastName").setValue("Petrov");
            $("#userEmail").setValue("petrov@mail.ru");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("9031235577");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("September");
            $(".react-datepicker__year-select").selectOption("1995");
            $(".react-datepicker__day--026:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("English").pressEnter();
            $("#subjectsInput").setValue("Biology").pressEnter();
            $("#subjectsInput").setValue("History").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath("giraffe.jpeg");
            $("#currentAddress").setValue("Moscow city");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Gurgaon")).click();
            $("#submit").click();
        });
        step("Проверяем результат", () -> {
            $(".modal-dialog").should(appear);
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table").$(byText("Student Name")).parent().shouldHave(text("Alex Petrov"));
            $(".table").$(byText("Student Email")).parent().shouldHave(text("petrov@mail.ru"));
            $(".table").$(byText("Gender")).parent().shouldHave(text("Male"));
            $(".table").$(byText("Mobile")).parent().shouldHave(text("9031235577"));
            $(".table").$(byText("Date of Birth")).parent().shouldHave(text("26 September,1995"));
            $(".table").$(byText("Subjects")).parent().shouldHave(text("English, Biology, History"));
            $(".table").$(byText("Hobbies")).parent().shouldHave(text("Sports"));
            $(".table").$(byText("Picture")).parent().shouldHave(text("giraffe.jpeg"));
            $(".table").$(byText("Address")).parent().shouldHave(text("Moscow city"));
            $(".table").$(byText("State and City")).parent().shouldHave(text("NCR Gurgaon"));
        });
    }
}