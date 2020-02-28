package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class HomePage extends PageBase {

    @FindBy(id = "small-searchterms")
    private WebElement searchInput;
    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class='top-menu notmobile']/li/a")
    private List<WebElement> menuItems;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ResultsPage searchFor(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchButton.click();

        return new ResultsPage(driver);
    }

    public void selectMenuItem(String itemName) {
        List<String> stringItems = getMenuItems();

        if(stringItems.contains(itemName)) {
            int itemIndex = stringItems.indexOf(itemName);
            menuItems.get(itemIndex).click();
        } else
            fail("Menu item " + itemName + " not available");
    }

//    TODO
    public void selectMenuItem(String itemName, String subCategoryName) {
        List<String> stringItems = getMenuItems();

        if(stringItems.contains(itemName)) {
            int itemIndex = stringItems.indexOf(itemName);
            menuItems.get(itemIndex).click();

        } else
            fail("Menu item " + itemName + " not available");
    }

    public List<String> getMenuItems() {
        List<String> stringItems = new ArrayList<>();
        for (WebElement item : menuItems) {
            stringItems.add(item.getText());
        }

        return stringItems;
    }
}
