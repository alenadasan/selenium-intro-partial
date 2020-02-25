package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase {

    @FindBy(id = "small-searchterms")
    private WebElement searchInput;
    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ResultsPage searchFor(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchButton.click();

        return new ResultsPage(driver);
    }
}
