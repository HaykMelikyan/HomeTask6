package googlecloudpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {
    private String pageUrl = BASE_URL;

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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        return this;
    }

    public SearchResultsPage searchText(String searchText) {
        Actions actions = new Actions(driver);
        actions.click(searchField)
                .build()
                .perform();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector("devsite-search[search-active]")));
        actions.sendKeys(searchField, searchText + Keys.ENTER)
                .build()
                .perform();
        return new SearchResultsPage(driver);
    }
}
