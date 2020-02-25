package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class RegisterPage extends PageBase{

    @FindBy(name = "Gender")
    private List<WebElement> genderRadioButtons;

    @FindBy(id = "FirstName")
    private WebElement firstNameInput;
    @FindBy(id = "LastName")
    private WebElement lastNameInput;

    @FindBy(name = "DateOfBirthDay")
    private WebElement dayOfBirthSelector;
    @FindBy(name = "DateOfBirthMonth")
    private WebElement monthOfBirthSelector;
    @FindBy(name = "DateOfBirthYear")
    private WebElement yearOfBirthSelector;

    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Company")
    private WebElement companyNameInput;

    @FindBy(id = "Newsletter")
    private WebElement newsletterSubscriptionCheckbox;

    @FindBy(id = "Password")
    private WebElement passwordInput;
    @FindBy(id = "ConfirmPassword")
    private WebElement confirmedPasswordInput;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerAs(String gender, String firstName, String lastName, int day, String month, int year,
                           String email, String companyName, boolean wantsNewsletter,
                           String password, String confirmedPassword) {

        setGender(gender);

        fillInFirstName(firstName);
        fillInLastName(lastName, lastNameInput);

        setDateOfBirth(day, month, year);

        fillInEmail(email);
        fillInCompanyName(companyName);

        setNewsletterSelection(wantsNewsletter);

        fillInPassword(password);
        fillInConfirmedPassword(confirmedPassword);

        clickRegister(registerButton);
    }

    public void setGender(String gender) {
        if(gender.equalsIgnoreCase("male"))
            genderRadioButtons.get(0).click();
        else
            genderRadioButtons.get(1).click();
    }

    public void fillInFirstName(String firstName) {
        fillInLastName(firstName, firstNameInput);
    }

    public void fillInLastName(String lastName, WebElement lastNameInput) {
        lastNameInput.sendKeys(lastName);
    }

    public void setDateOfBirth(int day, String month, int year) {
        new Select(dayOfBirthSelector).selectByVisibleText(String.valueOf(day));
        new Select(monthOfBirthSelector).selectByVisibleText(month);
        new Select(yearOfBirthSelector).selectByVisibleText(String.valueOf(year));
    }

    public void fillInEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void fillInCompanyName(String companyName) {
        companyNameInput.sendKeys(companyName);
    }

    public void setNewsletterSelection(boolean wantsNewsletter) {
        if(wantsNewsletter == true && newsletterSubscriptionCheckbox.isSelected() == false)
            clickRegister(newsletterSubscriptionCheckbox);
    }

    public void fillInPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void fillInConfirmedPassword(String confirmedPassword) {
        passwordInput.sendKeys(confirmedPassword);
    }

    public void clickRegister(WebElement registerButton) {
        registerButton.click();
    }
}
