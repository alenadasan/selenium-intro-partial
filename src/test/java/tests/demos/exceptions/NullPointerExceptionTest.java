package tests.demos.exceptions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;


/**
 * Created by Ale on 07/08/17.
 */
public class NullPointerExceptionTest {

    private WebDriver driver;
    private String str = "";

    @Test
    public void canNavigateToLoginPage() throws Exception {
        driver.get("https://demo.nopcommerce.com/login");
    }

    @Test
    public void canCallSubstringOnAString() throws Exception {
        System.out.println(str.substring(0, 1));
    }
}
