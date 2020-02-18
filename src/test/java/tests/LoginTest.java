package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Ale/workspace/chromedriver");
        driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/login");
    }

    @Test
    public void canLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "1qa2ws3ed");

        assertThat(loginPage.getLoginMessage(), is("Log out"));
    }

    @Test
    public void cannotLoginWithInvalidEmail() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@gmail.com", "123456");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nNo customer account found"));
    }

    @Test
    public void cannotLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "invalid");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }

    @Test
    public void cannotLoginWithNoPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
