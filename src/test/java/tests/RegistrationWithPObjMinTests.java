package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationWithPObjMinTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Olga")
                .setLastName("Ivanova")
                .setGender("Female")
                .setUserNumber("9031230099")
                .submit();

        registrationPage.checkResult("Student Name", "Olga Ivanova")
                .checkResult("Gender", "Female")
                .checkResult("Mobile","9031230099");

       // sleep(5000);
    }
}
