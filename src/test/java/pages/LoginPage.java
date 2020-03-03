package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {

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
    @FindBy(xpath = "//div[contains(@class, 'message-error')]")
    private WebElement errorMessage;

    private HeaderSection header;
    private FooterSection footer;

    public LoginPage(WebDriver driver) {
        super(driver);
        header = new HeaderSection(driver);
        footer = new FooterSection(driver);

        wait.until(ExpectedConditions.urlContains("login"));
    }

    public void loginAs(String email, String pass) {
        fillInEmail(email);
        fillInPassword(pass);
        clickLogin();
    }

    public void fillInEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillInPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        logInButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public HeaderSection getHeader() {
        return header;
    }

    public FooterSection getFooter() {
        return footer;
    }
}
