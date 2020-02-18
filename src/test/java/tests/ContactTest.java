package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ContactPage;
import pages.LoginPage;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ContactTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Ale/workspace/chromedriver");
        driver = new ChromeDriver();

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
        assertThat(contactPage.getEnquiry(), is(""));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
