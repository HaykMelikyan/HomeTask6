package googlecloudpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailEstimateForm extends BasePage {
    private final String BASE_CSS = "form[name = 'emailForm'] ";

    @FindBy(css = BASE_CSS + "input[type = 'email']")
    private WebElement emailInput;

    @FindBy(css = "button[aria-label = 'Send Email']")
    private WebElement buttonSendEmail;

    public EmailEstimateForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EmailEstimateForm waitUntilFormAppear() {
        waitUntilPageLoads();
        return this;
    }

    @Override
    protected BasePage waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        wait.until(ExpectedConditions.visibilityOf(buttonSendEmail));
        return this;
    }

    public EmailEstimateForm setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public void clickSendEmail() {
        buttonSendEmail.click();
    }
}
