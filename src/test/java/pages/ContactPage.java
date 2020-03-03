package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class ContactPage extends PageBase {

    @FindBy(id = "FullName")
    private WebElement fullNameInput;
    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Enquiry")
    private WebElement enquiryInput;
    @FindBy(name = "send-email")
    private WebElement sendEmailButton;

    @FindBy(className = "result")
    private WebElement confirmationMessage;
    @FindBy(xpath = "//span[contains(@id, '-error')]")
    private List<WebElement> errorMessages;

    public ContactPage(WebDriver driver) {
        super(driver);
        wait.until(urlContains("contactus"));
    }

    public void sendContact(String name, String email, String enquiry) {
        fillInName(name);
        fillInEmail(email);
        fillInEnquiry(enquiry);
        sendEmailButton.click();
    }

    public void fillInName(String name) {
        fullNameInput.clear();
        fullNameInput.sendKeys(name);
    }

    public void fillInEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void fillInEnquiry(String enquiry) {
        enquiryInput.clear();
        enquiryInput.sendKeys(enquiry);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public List<String> getErrorMessages() {
        List<String> messages = new ArrayList<String>();

        for (WebElement e : errorMessages) {
            messages.add(e.getText());
        }

        return messages;
    }

    public String getName() {
        return fullNameInput.getAttribute("value");
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public String getEnquiry() {
        return enquiryInput.getAttribute("value");
    }
}
