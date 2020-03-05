package tests.demos.exceptions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import tests.TestBase;


/**
 * Created by Ale on 07/08/17.
 */
public class NullPointerExceptionWithTestBaseTest extends TestBase {

    private WebDriver driver; // this object duplicates the one in the base class and is null

    @Test
    public void canNavigateToLoginPage() throws Exception {
        driver.get("https://demo.nopcommerce.com/login");
    }

}
