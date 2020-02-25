package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ResultsPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class SearchTest extends TestBase {

    private HomePage homePage;

    @BeforeEach
    void setUp() {
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @Test
    public void canSearchForAnExistingItem() {
        String query = "book";

        ResultsPage resultsPage = homePage.searchFor(query);
        List<String> productTitles = resultsPage.getProductTitles();

        for (String title : productTitles) {
            assertThat(title, containsStringIgnoringCase(query));
        }
        assertThat(productTitles.size(), greaterThan(0));
    }

    @Test
    void cannotSearchForEmptyString() {
        ResultsPage resultsPage = homePage.searchFor("");

        assertThat(resultsPage.getAlertNotification(), is("Please enter some search keyword"));
    }
}
