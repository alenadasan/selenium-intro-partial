package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class OrderTest extends TestBase {
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @Test
    public void canAddProductToCart() {
        homePage.addToCartFeaturedProductWithIndex(2);
        String productName = homePage.getFeaturedProductTitles().get(2);
        List<String> productsInCart = homePage.getHeaderSection().getProductTitlesFromCart();

        assertThat(productsInCart, contains(productName));
    }
}
