package googlecloudpages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private final String pageUrl = BASE_URL;

    @FindBy(name = "q")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage open() {
        driver.get(pageUrl);
        return this;
    }

    @Override
    public HomePage waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchField));
        return this;
    }

    public SearchResultsPage searchText(String searchText) {
        searchField.click();
        searchField.sendKeys(searchText + Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}
