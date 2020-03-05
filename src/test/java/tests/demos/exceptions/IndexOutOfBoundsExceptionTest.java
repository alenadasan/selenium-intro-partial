package tests.demos.exceptions;

import org.junit.jupiter.api.Test;
import pages.RegisterPage;
import tests.TestBase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Ale on 07/08/17.
 */
public class IndexOutOfBoundsExceptionTest extends TestBase {

    @Test
    public void canAccessThirdArrayElement() throws Exception {
        List<String> errorMessages = Arrays.asList("First error", "Second error");

        System.out.println(errorMessages.get(2));
    }

    @Test
    public void canCheckFifthErrorMessage() throws Exception {
        driver.get("https://demo.nopcommerce.com/register");

        RegisterPage signUpPage = new RegisterPage(driver);
        signUpPage.fillInFirstName("John");
        signUpPage.clickRegister();

        assertThat(signUpPage.getErrorMessages().get(4), is("Password is required."));
    }
}
