package googlecloudpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EstimationResultBar extends BasePage {
    private static final String BASE_XPATH = "//div[@ng-controller = 'CloudCartCtrl as cloudCartCtrl']/div//div[contains(normalize-space(), ";

    @FindBy(xpath = BASE_XPATH + "'VM class:')]")
    private WebElement vmClass;

    @FindBy(xpath = BASE_XPATH + "'Instance type:')]")
    private List<WebElement> instanceType;

    @FindBy(xpath = BASE_XPATH + "'Region:')]")
    private List<WebElement> region;

    @FindBy(xpath = BASE_XPATH + "'Commitment term:')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//div[@ng-controller = 'CloudCartCtrl as cloudCartCtrl']/div//b[contains(normalize-space(), 'Total Estimated Cost:')]")
    private WebElement totalPrice;

    @FindBy(css = "button[aria-label = 'Email Estimate']")
    private WebElement buttonEmailEstimate;

    public EstimationResultBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EstimationResultBar waitUntilResultsAppear() {
        waitUntilPageLoads();
        return this;
    }

    @Override
    protected BasePage waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(vmClass));
        wait.until(ExpectedConditions.visibilityOf(commitmentTerm));
        return this;
    }

    public String getVmClass() {
        String[] words = vmClass.getText().split(" ");
        return words[words.length - 1];
    }

    public String getInstanceType() {
        String[] words = instanceType.get(0).getText().split(" ");
        return words[words.length - 1];
    }

    public String getRegion() {
        String[] words = region.get(1).getText().split(" ");
        return words[words.length - 1];
    }

    public String getCommitmentTerm() {
        String[] words = commitmentTerm.getText().split(" ");
        return words[words.length - 2] + " " + words[words.length - 1];
    }

    public String getTotalPrice() {
        String[] words = totalPrice.getText().split(" ");
        return words[words.length - 4];
    }

    public EmailEstimateForm clickEmailEstimate() {
        buttonEmailEstimate.click();
        return new EmailEstimateForm(driver);
    }
}
