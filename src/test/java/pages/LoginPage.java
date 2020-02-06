package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement logInButton;

    @FindBy(tagName = "h1")
    private WebElement title;
}
