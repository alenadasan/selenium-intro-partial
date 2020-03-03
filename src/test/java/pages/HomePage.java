package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends PageBase {

    @FindBy(className = "product-title")
    private List<WebElement> featuredProductsTitles;
    @FindBy(xpath = "//input[@value='Add to cart']")
    private List<WebElement> addToCartButtons;

    private HeaderSection headerSection;
    private FooterSection footerSection;


    public HomePage(WebDriver driver) {
        super(driver);

        headerSection = new HeaderSection(driver);
        footerSection = new FooterSection(driver);
    }

    public List<String> getFeaturedProductTitles() {
        List<String> productTitles = new ArrayList<>();

        for (WebElement title : featuredProductsTitles) {
            productTitles.add(title.getText());
        }

        return productTitles;
    }

    public void addToCartFeaturedProductWithIndex(int index) {
        addToCartButtons.get(index).click();
        headerSection.waitForConfirmationBarToHide();
    }

    public HeaderSection getHeaderSection() {
        return headerSection;
    }

    public FooterSection getFooterSection() {
        return footerSection;
    }
}
