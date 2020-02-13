package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


public class FooterSection {

    @FindBy(xpath = "//ul[@class='networks']//a")
    private List<WebElement> socialMediaLinks;

    public void clickOnLinkWithIndex(int index) {
        try {
            socialMediaLinks.get(index).click();
        } catch (IndexOutOfBoundsException e) {
            fail("Social media link with index " + index + " was not available");
        }
    }
}
