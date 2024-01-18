package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
     private
       SelenideElement
            userNameInput=$("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput=$("#currentAddress"),
            permanentAddressInput=$("#permanentAddress"),
            submitButton=$("#submit"),
            output=$("#output");
    public TextBoxPage openPageTextBox() {
        open("/text-box");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
       }
    public TextBoxPage setUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }
    public TextBoxPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }
        public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }
    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    public TextBoxPage submit() {
        submitButton.click();

        return this;
    }
        public TextBoxPage outputInfoCheck(String key, String value){
       output.$(byId(key)).shouldHave(text(value));

        return this;
    }
}