package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage extends PageBase {

    @FindBy(className = "product-title")
    private List<WebElement> productTitleList;

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
}
