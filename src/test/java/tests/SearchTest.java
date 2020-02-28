package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.HomePage;
import pages.ResultsPage;

import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SearchTest extends TestBase {

    private HomePage homePage;

    @BeforeEach
    public void setUp() {
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

    @ParameterizedTest
    @ValueSource(strings = {"book", "computer", "HTC"})
    public void canSearchForExistingItems(String query) {
        ResultsPage resultsPage = homePage.searchFor(query);
        List<String> productTitles = resultsPage.getProductTitles();

        for (String title : productTitles) {
            assertThat(title, containsStringIgnoringCase(query));
        }
        assertThat(productTitles.size(), greaterThan(0));
    }

    @Test
    public void cannotSearchForEmptyString() {
        ResultsPage resultsPage = homePage.searchFor("");

        assertThat(resultsPage.getAlertNotification(), is("Please enter some search keyword"));
    }

    @ParameterizedTest
    @MethodSource("stringsProvider")
    public void cannotSearchForInvalidItems(String query, String expectedWarningMessage) {
        ResultsPage resultsPage = homePage.searchFor(query);

        assertThat(resultsPage.getWarningMessage(), is(expectedWarningMessage));
    }

    public static Stream<Arguments> stringsProvider() {
        return Stream.of(
                arguments("t", "Search term minimum length is 3 characters"),
                arguments("452", "No products were found that matched your criteria."),
                arguments("santa hat", "No products were found that matched your criteria.")
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/NopCommerceSearchItems.csv", numLinesToSkip = 1)
    public void canSearchForExistingItemsFromCSVFile(String query) {
        ResultsPage resultsPage = homePage.searchFor(query);
        List<String> productTitles = resultsPage.getProductTitles();

        assertThat(productTitles.size(), greaterThan(0));
        for (String title : productTitles) {
            assertThat(title, containsStringIgnoringCase(query));
        }
    }

}
