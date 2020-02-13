package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LoginTest {

    @Test
    public void canLoginWithValidCredentials() {
        System.setProperty("webdriver.chrome.driver", "/Users/Ale/workspace/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demo.nopcommerce.com/login");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginAs("ale.nadasan@mailnesia.com", "1qa2ws3ed");

        assertThat(loginPage.getLoginMessage(), is("Log out"));

        driver.quit();
    }
}
