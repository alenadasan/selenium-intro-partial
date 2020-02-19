package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage {

    @FindBy(className = "product-title")
    private List<WebElement> productTitleList;

    private WebDriver driver;

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getProductTitles() {
        List<String> titles = new ArrayList<String>();

        for(WebElement e : productTitleList) {
            titles.add(e.getText());
        }

        return titles;
    }
}
