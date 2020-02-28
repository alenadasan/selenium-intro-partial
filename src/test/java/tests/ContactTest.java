package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ContactPage;
import pages.LoginPage;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ContactTest extends TestBase {

    @BeforeEach
    public void setUp() {
        driver.get("https://demo.nopcommerce.com/contactus");
    }

    @Test
    public void canSendAContactMessage_whenEnteringValidCredentials() {
        ContactPage contactPage = new ContactPage(driver);

        contactPage.sendContact("Jim", "jim@mailnesia.com", UUID.randomUUID().toString());

        assertThat(contactPage.getConfirmationMessage(), is("Your enquiry has been successfully sent to the store owner."));
    }

    @Test
    public void cannotSendAContactMessage_withoutFillingInMandatoryFields() {
        ContactPage contactPage = new ContactPage(driver);

        contactPage.sendContact("", "", "");

        assertThat(contactPage.getErrorMessages(), contains("Enter your name", "Enter email", "Enter enquiry"));
    }

    @Test
    void whenUserIsLoggedIn_personalDetailsAreFilledInTheContactForm() {
        driver.get("https://demo.nopcommerce.com/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "1qa2ws3ed");
        ContactPage contactPage = loginPage.getFooter().goToContactPage();

        assertThat(contactPage.getName(), is("Ale Test"));
        assertThat(contactPage.getEmail(), is("ale.nadasan@mailnesia.com"));
    }

    @Test
    void whenUserIsLoggedIn_personalDetailsAreFilledInTheContactForm_withAssertAll() {
        driver.get("https://demo.nopcommerce.com/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "1qa2ws3ed");
        ContactPage contactPage = loginPage.getFooter().goToContactPage();

        assertAll("Form should contains logged in user's details",
                () -> assertThat(contactPage.getName(), is("Ale Test")),
                () -> assertThat(contactPage.getEmail(), is("ale.nadasan@mailnesia.com"))
        );
    }
}
