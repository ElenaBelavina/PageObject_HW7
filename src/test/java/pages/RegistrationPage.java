package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTable;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
     private SelenideElement firstNameInput = $("#firstName"),
                             lastNameInput = $("#lastName"),
                             userEmailInput = $("#userEmail"),
                             genderWrapper = $("#genterWrapper"),
                             userNumberInput = $("#userNumber"),
                             calendarInput = $("#dateOfBirthInput"),
                             subjectsInput = $("#subjectsInput"),
                             hobbiesWrapper = $("#hobbiesWrapper"),
                             uploadPict=$("#uploadPicture"),
                             currentAddressInput=$("#currentAddress"),
                             permanentAddressInput=$("#permanentAddress"),
                             stateInput=$("#state"),
                             cityInput=$("#city"),
                             stateCityWrapperInput=$("#stateCity-wrapper"),
                             submitButton=$("#submit"),
                             modalDialog=$(".modal-dialog");

    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        return this;
    }
    public RegistrationPage closeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }
    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String value) {
        uploadPict.uploadFromClasspath(value);

        return this;
    }
    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }
    public RegistrationPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }
    public RegistrationPage setState(String value) {
        stateInput.click();
        stateCityWrapperInput.$(byText(value)).click();

        return this;
    }
    public RegistrationPage setCity(String value) {
        cityInput.click();
        stateCityWrapperInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();

        return this;
    }
    public RegistrationPage checkResult(String key, String value) {
        ResultTable.checkResults(key,value);

        return this;
    }

 

      public RegistrationPage checkModalDialog() {
        modalDialog.shouldNot(appear);
        return this;
    }

}

