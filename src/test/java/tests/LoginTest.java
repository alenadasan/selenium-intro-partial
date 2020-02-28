package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.LoginPage;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LoginTest extends TestBase {

    private LoginPage loginPage;

    private final static String TEST_EMAIL = "ale.nadasan@mailnesia.com";
    private final static String TEST_PASSWORD = "1qa2ws3ed";

    @BeforeEach
    public void setUp() {
        driver.get("https://demo.nopcommerce.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void canLoginWithValidCredentials() {
        loginPage.loginAs(TEST_EMAIL, TEST_PASSWORD);

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
        loginPage.loginAs(TEST_EMAIL, "invalid");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }

    @Test
    public void cannotLoginWithNoPassword() {
        loginPage.loginAs(TEST_EMAIL, "");

        assertThat(loginPage.getErrorMessage(), is("Login was unsuccessful. Please correct the errors and try again." +
                "\nThe credentials provided are incorrect"));
    }

    @ParameterizedTest
    @MethodSource("credentialsAndErrorMessagesProvider")
    public void cannotLoginWithInvalidCredentials(String email, String pass, String expectedError) {
        loginPage.loginAs(email, pass);

        assertThat(loginPage.getErrorMessage(), is(expectedError));
    }

    static Stream<Arguments> credentialsAndErrorMessagesProvider() {
        return Stream.of(
                arguments("ale.nadasan@mailnesia.com", "wrongPass", "Login was unsuccessful. Please correct the errors and try again." +
                        "\nThe credentials provided are incorrect"),
                arguments("wrongEmail@mailensia.com", "pass", "Login was unsuccessful. Please correct the errors and try again." +
                        "\nNo customer account found"),
                arguments("ale.nadasan@mailnesia.com", "", "Login was unsuccessful. Please correct the errors and try again." +
                        "\nThe credentials provided are incorrect")
        );
    }
}
