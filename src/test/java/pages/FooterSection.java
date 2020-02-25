package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;


public class FooterSection extends PageBase {

    @FindBy(partialLinkText = "Contact us")
    private WebElement contactUsLink;
    @FindBy(xpath = "//ul[@class='networks']//a")
    private List<WebElement> socialMediaLinks;

    public FooterSection(WebDriver driver) {
        super(driver);
    }

    public ContactPage goToContactPage() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(contactUsLink));
        contactUsLink.click();

        return new ContactPage(driver);
    }

    public void clickOnLinkWithIndex(int index) {
        try {
            socialMediaLinks.get(index).click();
        } catch (IndexOutOfBoundsException e) {
            fail("Social media link with index " + index + " was not available");
        }
    }
}
