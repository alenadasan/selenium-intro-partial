package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends PageBase {

    @FindBy(className = "product-title")
    private List<WebElement> productTitleList;
    @FindBy(xpath = "//div[@class='search-results']/div[@class != 'product-grid']")
    private WebElement warningMessage;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductTitles() {
        List<String> titles = new ArrayList<String>();

        for(WebElement e : productTitleList) {
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
}
