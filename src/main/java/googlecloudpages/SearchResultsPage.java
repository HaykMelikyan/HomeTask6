package googlecloudpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.drivers.WebDriverFactory;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//a[normalize-space() = 'Google Cloud Platform Pricing Calculator']")
    private WebElement firstResult;

    private final By allResultsLoc = By.xpath("//div[contains(@class, gsc-result)]/a[@class='gs-title']");

    public SearchResultsPage() {
        driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    public SearchResultsPage waitUntilPageLoads() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .numberOfElementsToBeMoreThan(allResultsLoc, 1));
        return this;
    }

    public PricingCalculatorPage clickFirstResult() {
        firstResult.click();
        return new PricingCalculatorPage();
    }
}
