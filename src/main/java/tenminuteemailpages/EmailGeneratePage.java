package tenminuteemailpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.drivers.WebDriverFactory;

public class EmailGeneratePage {
    WebDriver driver;
    private String pageUrl = "https://10minutemail.com";

    @FindBy(id = "mail_address")
    private WebElement mailAddress;

    @FindBy(id = "copy_address")
    private WebElement buttonCopyEmail;

    @FindBy(className = "mail_message")
    private WebElement receivedMail;

    @FindBy(css = "tr[id = 'mobilepadding'] h2")
    private WebElement monthlyCost;

    public EmailGeneratePage() {
        driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public EmailGeneratePage open() {
        driver.get(pageUrl);
        return this;
    }

    public EmailGeneratePage waintUntilPageLoads() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.attributeToBeNotEmpty(mailAddress, "value"));
        return this;
    }

    public EmailGeneratePage copyEmail() {
        buttonCopyEmail.click();
        return this;
    }

    public EmailGeneratePage openReceivedMail() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.visibilityOf(receivedMail));
        receivedMail.click();
        return this;
    }

    public String getMonthlyCost() {
        String[] words = monthlyCost.getText().split(" ");
        return words[words.length - 1];
    }

}
