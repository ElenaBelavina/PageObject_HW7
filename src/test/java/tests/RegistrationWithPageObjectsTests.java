package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationWithPageObjectsTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void successfulRegistrationTest() {
        registrationPage.openPage().closeBanners()
                        .setFirstName("Alex")
                        .setLastName("Petrov")
                        .setEmail("petrov@mail.ru")
                        .setGender("Male")
                        .setUserNumber("9031235577")
                        .setDateOfBirth("26","September","1995")
                        .setSubjects("English")
                        .setSubjects("Biology")
                        .setSubjects("History")
                        .setHobbies("Sports")
                        .uploadPicture("giraffe.jpeg")
                        .setCurrentAddress("Moscow city")
                        .setState("NCR")
                        .setCity("Gurgaon")
                        .submit();

        registrationPage.checkResult("Student Name", "Alex Petrov")
                        .checkResult("Student Email", "petrov@mail.ru")
                        .checkResult("Gender", "Male")
                        .checkResult("Mobile","9031235577")
                        .checkResult("Date of Birth","26 September,1995")
                        .checkResult("Subjects","English, Biology, History")
                        .checkResult("Hobbies","Sports")
                        .checkResult("Picture","giraffe.jpeg")
                        .checkResult("Address","Moscow city")
                        .checkResult("State and City","NCR Gurgaon");

        //sleep(5000);
    }

    @Test
    void registrationMinTest(){
        registrationPage.openPage().closeBanners()
                        .setFirstName("Olga")
                        .setLastName("Ivanova")
                        .setGender("Female")
                        .setUserNumber("9031230099")
                        .submit();

        registrationPage.checkResult("Student Name", "Olga Ivanova")
                        .checkResult("Gender", "Female")
                        .checkResult("Mobile","9031230099");
        //sleep(5000);
    }

    @Test
    void registrationNegativeTest(){
        registrationPage.openPage().closeBanners()
                        .setLastName("Petrov")
                        .setEmail("petrov@mail.ru")
                        .setGender("Male")
                        .setUserNumber("9031235577")
                        .setDateOfBirth("26","September","1995")
                        .setSubjects("English")
                        .setSubjects("Biology")
                        .setSubjects("History")
                        .setHobbies("Sports")
                        .uploadPicture("giraffe.jpeg")
                        .setCurrentAddress("Moscow city")
                        .setState("NCR")
                        .setCity("Gurgaon")
                        .submit();

        registrationPage.checkResult("Student Name", "Alex Petrov")
                        .checkResult("Student Email", "petrov@mail.ru")
                        .checkResult("Gender", "Male")
                        .checkResult("Mobile","9031235577")
                        .checkResult("Date of Birth","26 September,1995")
                        .checkResult("Subjects","English, Biology, History")
                        .checkResult("Hobbies","Sports")
                        .checkResult("Picture","giraffe.jpeg")
                        .checkResult("Address","Moscow city")
                        .checkResult("State and City","NCR Gurgaon");

         //sleep(5000);
    }
}
