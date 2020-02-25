package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginTest extends TestBase {

    private LoginPage loginPage;

    private final String email = "ale.nadasan@mailnesia.com";
    private final String pass = "1qa2ws3ed";

    @BeforeEach
    public void setUp() {
        driver.get("https://demo.nopcommerce.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void canLoginWithValidCredentials() {
        loginPage.loginAs(email, pass);

        assertThat(loginPage.getLoginMessage(), is("Log out"));
    }

    @Test
    public void cannotLoginWithInvalidEmail() {
        loginPage.loginAs("ale.nadasan@gmail.com", "123456");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nNo customer account found"));
    }

    @Test
    public void cannotLoginWithInvalidPassword() {
        loginPage.loginAs(email, "invalid");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }

    @Test
    public void cannotLoginWithNoPassword() {
        loginPage.loginAs(email, "");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }
}
