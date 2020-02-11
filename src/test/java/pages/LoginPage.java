package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Password")
    private WebElement passwordInput;
    @FindBy(xpath = "//input[@value='Log in']")
    private WebElement logInButton;

    @FindBy(partialLinkText = "Forgot password?")
    private WebElement forgotPasswordLink;

    @FindBy(id = "Email-error")
    private WebElement errorMessage;


    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
