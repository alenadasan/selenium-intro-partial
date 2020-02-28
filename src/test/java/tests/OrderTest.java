package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class OrderTest extends TestBase {
    private HomePage homePage;

    @BeforeEach
    void setUp() {
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

//    TODO
    @Test
    public void canAddProductToCart() {

    }
}
