package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {

    @Test
    public void canAddTwoPlusTwo() {
        int sum = 2 + 2;

        assertEquals(sum,4);
    }

    @Test
    public void canAccessGoogle() {
        System.setProperty("webdriver.chrome.driver", "/Users/Ale/workspace/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("https://google.com/");

        assertThat(driver.getCurrentUrl(), containsString("google"));
        assertThat(driver.getCurrentUrl(), is("https://www.google.com/"));

        driver.quit();
    }
}