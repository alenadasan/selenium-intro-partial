package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleSearchPage {

    @FindBy(name = "q")
    private WebElement searchInput;
    @FindBy(xpath = "(//input[@name='btnK'])[2]")
    private WebElement searchButton;


}
