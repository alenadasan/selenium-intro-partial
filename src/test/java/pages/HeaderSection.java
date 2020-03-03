package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class HeaderSection extends PageBase {

    @FindBy(partialLinkText = "Log")
    private WebElement logInMenuLink;

    @FindBy(className = "cart-label")
    private WebElement shoppingCartLink;
    @FindBy(className = "name")
    private List<WebElement> cartProductTitles;

    @FindBy(id = "bar-notification")
    private WebElement barNotification;
    @FindBy(className = "close")
    private WebElement notificationCloseButton;

    @FindBy(id = "small-searchterms")
    private WebElement searchInput;
    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class='top-menu notmobile']/li/a")
    private List<WebElement> menuItems;


    public HeaderSection(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLogin() {
        assumeTrue(logInMenuLink.getText().contains("Log in"));
        logInMenuLink.click();

        return new LoginPage(driver);
    }

    public HomePage logOut() {
        assumeTrue(logInMenuLink.getText().contains("Log out"));
        logInMenuLink.click();

        return new HomePage(driver);
    }

    public String getLoginMessage() {
        return logInMenuLink.getText();
    }

    public boolean isUserLoggedIn() {
        return logInMenuLink.getText().contains("Log out");
    }

    public List<String> getProductTitlesFromCart() {
        viewCartContent();

        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(cartProductTitles));
        } catch (TimeoutException e) {
        }

        List<String> titles = new ArrayList<>();
        for (WebElement productTitle : cartProductTitles) {
            titles.add(productTitle.getText());
        }

        return titles;
    }

    private void viewCartContent() {
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartLink).perform();
    }

    public void waitForConfirmationBarToHide() {
        wait.until(ExpectedConditions.visibilityOf(barNotification));
        notificationCloseButton.click();
        wait.until(ExpectedConditions.invisibilityOf(barNotification));
    }

    public ResultsPage searchFor(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
        searchButton.click();

        return new ResultsPage(driver);
    }

    public void selectMenuItem(String itemName) {
        List<String> stringItems = getMenuItems();

        if (stringItems.contains(itemName)) {
            int itemIndex = stringItems.indexOf(itemName);
            menuItems.get(itemIndex).click();
        } else
            fail("Menu item " + itemName + " not available");
    }
    //    TODO
    public void selectMenuItem(String itemName, String subCategoryName) {
        List<String> stringItems = getMenuItems();

        if (stringItems.contains(itemName)) {
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
