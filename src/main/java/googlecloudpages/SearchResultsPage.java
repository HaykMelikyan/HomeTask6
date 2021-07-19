package googlecloudpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[normalize-space() = 'Google Cloud Platform Pricing Calculator']")
    private WebElement firstResult;

    private final By allResultsLoc = By.xpath("//div[contains(@class, gsc-result)]/a[@class='gs-title']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public SearchResultsPage waitUntilPageLoads() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .numberOfElementsToBe(allResultsLoc, 10));
        return this;
    }

    public PricingCalculatorPage clickFirstResult() {
        firstResult.click();
        return new PricingCalculatorPage(driver);
    }
}
