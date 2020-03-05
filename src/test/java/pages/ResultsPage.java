package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class ResultsPage extends PageBase {

    @FindBy(className = "product-title")
    private List<WebElement> productTitlesList;
    @FindBy(xpath = "//input[@value='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(xpath = "//div[@class='search-results']/div[@class != 'product-grid']")
    private WebElement warningMessage;

    private HeaderSection headerSection;

    public ResultsPage(WebDriver driver) {
        super(driver);
        headerSection = new HeaderSection(driver);
    }

    public List<String> getProductTitles() {
        List<String> titles = new ArrayList<String>();

        for (WebElement e : productTitlesList) {
            titles.add(e.getText());
        }

        return titles;
    }

    public String getAlertNotification() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String getWarningMessage() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText();
    }

    public void addToCartProductWithIndex(int index) {
        try {
            wait.until(visibilityOfAllElements(addToCartButtons));
            addToCartButtons.get(index).click();
        } catch (IndexOutOfBoundsException e) {
            fail("Add to cart button with index " + index + " not available");
        }
        headerSection.waitForConfirmationBarToHide();
    }

    public HeaderSection getHeaderSection() {
        return headerSection;
    }
}
