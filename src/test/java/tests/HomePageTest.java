package tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class HomePageTest extends TestBase {

    @Disabled("this test will fail due to a broken link for the RSS link (bug in application)")
    @Test
    public void socialLinksWork() {
        List<String> expectURLs = Arrays.asList(
                "https://www.facebook.com/nopCommerce",
                "https://twitter.com/nopCommerce",
                "news/rss/",
                "https://www.youtube.com/user/nopCommerce"
        );

        driver.get("https://demo.nopcommerce.com/");
        HomePage homePage = new HomePage(driver);

        for (int i = 0; i < 4; i++) {
            homePage.getFooterSection().clickOnLinkWithIndex(i);

            assertThat(driver.getCurrentUrl(), containsString(expectURLs.get(i)));

            driver.close();
            homePage.getFooterSection().switchBackToMainPageFromSocialMediaPage();
        }
    }

}
