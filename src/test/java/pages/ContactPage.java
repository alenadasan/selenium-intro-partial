package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ContactPage {

    @FindBy(xpath = "//span[contains(@id, '-error')]")
    private List<WebElement> errorMessages;


    public List<String> getErrorMessages() {
        List<String> messages = new ArrayList<String>();

        for (WebElement e : errorMessages) {
            messages.add(e.getText());
        }

        return messages;
    }
}
