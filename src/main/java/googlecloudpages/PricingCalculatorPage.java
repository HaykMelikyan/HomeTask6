package googlecloudpages;

import optionpickers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.drivers.WebDriverFactory;

import java.util.List;

public class PricingCalculatorPage extends BasePage {
    private static final String DROPDOWN_OPTION_BASE_XPATH = "//div[@class = 'md-select-menu-container md-active md-clickable']//md-option[@value = '";

    @FindBy(xpath = "//md-input-container[@class = 'flex']/input[@name = 'quantity']")
    private WebElement numberOfInstancesInput;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.computeServer.instance']")
    private WebElement machineTypeDropdown;

    @FindBy(name = "nodesCount")
    private WebElement numberOfNodesInput;

    @FindBy(css = "md-checkbox[aria-label = 'Add GPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.soleTenant.gpuCount']")
    private WebElement numberOfGpusDropdown;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.soleTenant.gpuType']")
    private WebElement gpuTypeDropdown;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.soleTenant.ssd']")
    private WebElement localSsdDropdown;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.soleTenant.location']")
    private WebElement datacenterLocationDropdown;

    @FindBy(css = "md-select[ng-model = 'listingCtrl.soleTenant.cud']")
    private WebElement commitedUsageDropdown;

    @FindBy(xpath = "//button[normalize-space() = 'Add to Estimate']")
    private List<WebElement> buttonAddToEstimate;

    public PricingCalculatorPage() {
        driver = WebDriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @Override
    public PricingCalculatorPage waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        switchToInsideFrame();
        wait.until(ExpectedConditions.visibilityOf(numberOfInstancesInput));
        wait.until(ExpectedConditions.visibilityOf(numberOfNodesInput));
        switchToOutsideFrame();
        return this;
    }

    public PricingCalculatorPage inputNumberOfInstances(int numberOfInstances) {
        numberOfInstancesInput.sendKeys(Integer.toString(numberOfInstances));
        return this;
    }

    public PricingCalculatorPage inputNumberOfNodes(int numberOfNodes) {
        numberOfNodesInput.sendKeys(Integer.toString(numberOfNodes));
        return this;
    }

    public PricingCalculatorPage setMachineType(MachineType machineType) {
        machineTypeDropdown.click();
        waitAndClick("//md-optgroup[@label = 'standard']/md-option[normalize-space() = '" + machineType.getOptionText() + "']");
        return this;
    }

    public PricingCalculatorPage clickAddGpuCheckbox() {
        addGpuCheckbox.click();
        return this;
    }

    public PricingCalculatorPage setNumberOfGpus(NumberOfGpus numberOfGpus) {
        setDropdownOption(
                numberOfGpusDropdown,
                DROPDOWN_OPTION_BASE_XPATH + numberOfGpus.getOptionText() + "']"
        );
        return this;
    }

    public PricingCalculatorPage setGpuType(GpuType gpuType) {
        setDropdownOption(
                gpuTypeDropdown,
                DROPDOWN_OPTION_BASE_XPATH + gpuType.getOptionText() + "']"
        );
        return this;
    }

    public PricingCalculatorPage setLocalSsd(LocalSsd localSsd) {
        setDropdownOption(
                localSsdDropdown,
                DROPDOWN_OPTION_BASE_XPATH + localSsd.getOptionText() + "']"
        );
        return this;
    }

    public PricingCalculatorPage setDatacenterLocation(DatacenterLocation datacenterLocation) {
        setDropdownOption(
                datacenterLocationDropdown,
                DROPDOWN_OPTION_BASE_XPATH + datacenterLocation.getOptionText() + "']"
        );
        return this;
    }

    public PricingCalculatorPage setCommitedUsage(CommitedUsage commitedUsage) {
        setDropdownOption(
                commitedUsageDropdown,
                DROPDOWN_OPTION_BASE_XPATH + commitedUsage.getOptionText() + "']"
        );
        return this;
    }

    public EstimationResultBar clickAddToEstimates() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", buttonAddToEstimate.get(0));
        javascriptExecutor.executeScript("arguments[0].click();", buttonAddToEstimate.get(1));
        return new EstimationResultBar();
    }

    private void setDropdownOption(WebElement dropdown, String xpath) {
        dropdown.click();
        waitAndClick(xpath);
    }

    private void waitAndClick(String xpath) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions
                        .visibilityOfElementLocated(
                                By.xpath(xpath)))
                .click();
    }

    public PricingCalculatorPage switchToInsideFrame() {
        driver.switchTo().frame(0)
                .switchTo().frame(driver.findElement(By.id("myFrame")));
        return this;
    }

    public PricingCalculatorPage switchToOutsideFrame() {
        driver.switchTo().parentFrame()
                .switchTo().parentFrame();
        return this;
    }
}
