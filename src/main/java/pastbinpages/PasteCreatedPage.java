package pastbinpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.drivers.WebDriverFactory;

public class PasteCreatedPage extends BasePage {

    @FindBy(css = "div.highlighted-code > div.top-buttons > div.left > a")
    private WebElement highlighting;

    @FindBy(css = "div.highlighted-code > div.source")
    private WebElement code;

    public PasteCreatedPage() {
        driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    public PasteCreatedPage waitUntilPageLoads() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(By.className("post-view")));
        return this;
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHighlighting() {
        return highlighting.getText();
    }

    public String getCode() {
        return code.getText();
    }
}
