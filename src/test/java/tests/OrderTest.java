package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.ResultsPage;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class OrderTest extends TestBase {
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @Test
    public void canAddAFeaturedProductToCart() {
        homePage.addToCartFeaturedProductWithIndex(2);

        String productName = homePage.getFeaturedProductTitles().get(2);
        List<String> productsInCart = homePage.getHeaderSection().getProductTitlesFromCart();

        assertThat(productsInCart, contains(productName));
    }

    @Test
    public void canAddTheSameFeaturedProductToCartMultipleTimes() {
        homePage.addToCartFeaturedProductWithIndex(2);
        homePage.addToCartFeaturedProductWithIndex(2);

        String productName = homePage.getFeaturedProductTitles().get(2);
        List<String> productTitlesInCart = homePage.getHeaderSection().getProductTitlesFromCart();
        List<Integer> productQuantitiesInCart = homePage.getHeaderSection().getProductQuantitiesFromCart();

        assertThat(productTitlesInCart, contains(productName));
        assertThat(productQuantitiesInCart, contains(2));
    }

    @Test
    public void canAddMultipleProductsToCart_fromTheResultsPage() {
        ResultsPage resultsPage = homePage.getHeaderSection().searchFor("book");
        resultsPage.addToCartProductWithIndex(1);
        resultsPage.addToCartProductWithIndex(2);

        List<String> productTitles = resultsPage.getProductTitles();
        List<String> productsInCart = resultsPage.getHeaderSection().getProductTitlesFromCart();

        assertThat(productsInCart, containsInAnyOrder(productTitles.get(1), productTitles.get(2)));
    }

    @Test
    public void canAddMultipleProductsToCart_fromTheMainMenu() {
        List<String> addedProductTitles = new ArrayList<>();

        ResultsPage resultsPage = homePage.getHeaderSection().selectMenuItem("Books");
        resultsPage.addToCartProductWithIndex(0);
        addedProductTitles.add(resultsPage.getProductTitles().get(0));

        ResultsPage updatedResultsPage = resultsPage.getHeaderSection().selectMenuItem("Apparel", "Accessories");
        updatedResultsPage.addToCartProductWithIndex(1);
        addedProductTitles.add(updatedResultsPage.getProductTitles().get(1));

        List<String> productTitlesFromCart = updatedResultsPage.getHeaderSection().getProductTitlesFromCart();

        assertThat(productTitlesFromCart, containsInAnyOrder(addedProductTitles.get(0), addedProductTitles.get(1)));
    }
}
