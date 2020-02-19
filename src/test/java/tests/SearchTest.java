package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.ResultsPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;

public class SearchTest {

    @Test
    public void canSearchForAnExistingItem() {
        System.setProperty("webdriver.chrome.driver", "/Users/Ale/workspace/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("https://demo.nopcommerce.com/");
        HomePage homePage = new HomePage(driver);
        ResultsPage resultsPage = homePage.searchFor("book");
        List<String> productTitles = resultsPage.getProductTitles();

        for (String title : productTitles) {
            assertThat(title, containsStringIgnoringCase("book"));
        }
        assertThat(productTitles.size(), greaterThan(0));

        driver.quit();
    }
}
