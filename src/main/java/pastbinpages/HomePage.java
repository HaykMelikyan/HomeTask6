package pastbinpages;

import optionpickers.Expiration;
import optionpickers.Highlighting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.drivers.WebDriverFactory;

public class HomePage extends BasePage {
    private String pageUrl = BASE_URL;

    @FindBy(id = "postform-text")
    private WebElement codeInput;

    @FindBy(id = "select2-postform-format-container")
    private WebElement highlightingDropdown;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationDropdown;

    @FindBy(id = "postform-name")
    private WebElement nameInput;

    @FindBy(xpath = "//button[text() = 'Create New Paste']")
    private WebElement createButton;

    public HomePage() {
        driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public HomePage open() {
        driver.get(pageUrl);
        return this;
    }

    @Override
    public HomePage waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(codeInput));
        wait.until(ExpectedConditions.visibilityOf(expirationDropdown));
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        return this;
    }

    public HomePage inputCode(String code) {
        codeInput.sendKeys(code);
        return this;
    }

    public HomePage setHighlighting(Highlighting highlighting) {
        highlightingDropdown.click();
        waitAndClick("//ul[@id = 'select2-postform-format-results']//li[text() = '" + highlighting.getOptionText() + "']");
        return this;
    }

    public HomePage setExpiration(Expiration expiration) {
        expirationDropdown.click();
        waitAndClick("//ul[@id = 'select2-postform-expiration-results']/li[text() = '" + expiration.getOptionText() + "']");
        return this;
    }

    private void waitAndClick(String xpath) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath(xpath)))
                .click();
    }

    public HomePage inputName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public PasteCreatedPage clickCreateNewPaste() {
        createButton.click();
        return new PasteCreatedPage();
    }
}
