package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

public class TextBoxTests extends TestBase {

        TextBoxPage textBoxPage = new TextBoxPage();

        @Test
        void fillFormTest() {
            textBoxPage.openPageTextBox()
                       .setUserName("Valentino")
                       .setEmail("valentino@gmail.com")
                       .setCurrentAddress("Green Street")
                       .setPermanentAddress("Russia, Moscow")
                       .submit();

            textBoxPage.outputInfoCheck("name", "Valentino")
                       .outputInfoCheck("email", "valentino@gmail.com")
                       .outputInfoCheck("currentAddress", "Green Street")
                       .outputInfoCheck("permanentAddress", "Russia, Moscow");

          //  sleep(5000);
        }
    }