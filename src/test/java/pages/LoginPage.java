package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {

    @FindBy(partialLinkText = "Log")
    private WebElement logInMenuLink;

    @FindBy(tagName = "h1") //@FindBy(xpath="//h1")
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

    private FooterSection footer;

    protected WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        footer = new FooterSection(driver);
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

    public String getLoginMessage() {
        return logInMenuLink.getText();
    }

    public boolean isUserLoggedIn() {
        return logInMenuLink.getText().contains("Log out");
    }

    public FooterSection getFooter() {
        return footer;
    }
}
