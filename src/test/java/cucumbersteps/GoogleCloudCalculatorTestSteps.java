package cucumbersteps;

import googlecloudpages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import optionpickers.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tenminuteemailpages.EmailGeneratePage;
import utils.drivers.WebDriverFactory;

import java.util.ArrayList;

public class GoogleCloudCalculatorTestSteps {
    HomePage homePage;
    SearchResultsPage searchResultsPage;
    PricingCalculatorPage pricingCalculatorPage;
    EstimationResultBar estimationResultBar;
    EmailEstimateForm emailEstimateForm;
    EmailGeneratePage emailGeneratePage;

    ArrayList<String> tabs;
    String expectedMonthlyCost;

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriver getDriver() {
        return driver.get();
    }

    @Before
    public void setupDriver() {
        driver.set(WebDriverFactory.getDriver());
        getDriver().manage().window().maximize();
    }

    @After
    public void closeBrowser() {
        getDriver().quit();
        driver.set(null);
        WebDriverFactory.removeDriver();
    }

    @Then("the user opens google cloud website")
    public void openGoogleCloudWebsite() {
        homePage = new HomePage();
        homePage.open().waitUntilPageLoads();
    }

    @Then("the user clicks on the search button")
    public void clickOnSearchButton() {
        homePage.clickSearchButton();
    }

    @Then("^search for (.*)$")
    public void searchForCalculator(String searchText) {
        searchResultsPage = homePage.searchText(searchText).waitUntilPageLoads();
    }

    @Then("clicks on the first result")
    public void clicksOnFirstResult() {
        pricingCalculatorPage = searchResultsPage.clickFirstResult().waitUntilPageLoads();
    }

    @Then("^inputs the number of instances (.*)$")
    public void inputNumberOfInstances(int numberOfInstances) {
        pricingCalculatorPage
                .switchToInsideFrame()
                .inputNumberOfInstances(numberOfInstances)
                .switchToOutsideFrame();
    }

    @Then("sets machine type to e2 standard 8")
    public void setMachineTypeToE2Standard8() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setMachineType(MachineType.E2_STANDARD_8)
                .switchToOutsideFrame();
    }

    @Then("^inputs the number of nodes (.*)$")
    public void inputNumberOfNodes(int numberOfNodes) {
        pricingCalculatorPage
                .switchToInsideFrame()
                .inputNumberOfNodes(numberOfNodes)
                .switchToOutsideFrame();
    }

    @Then("checks \"Add GPU\" checkbox")
    public void checkAddGpuCheckbox() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .clickAddGpuCheckbox()
                .switchToOutsideFrame();
    }

    @Then("sets the number of GPU-s to 4")
    public void setNumberOfGpuTo4() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setNumberOfGpus(NumberOfGpus.FOUR)
                .switchToOutsideFrame();
    }

    @Then("sets the GPU type to Nvidia Tesla V100")
    public void setGpuTypeToNvidiaTeslaV100() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setGpuType(GpuType.NVIDIA_TESLA_V100)
                .switchToOutsideFrame();
    }

    @Then("sets the local SSD storage to 24x375GB")
    public void setLocalSsdTo24x375Gb() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setLocalSsd(LocalSsd.STORAGE_24X375GB)
                .switchToOutsideFrame();
    }

    @Then("sets the datacenter location to Frankfurt")
    public void setDatacenterLocationToFrankfurt() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setDatacenterLocation(DatacenterLocation.FRANKFURT)
                .switchToOutsideFrame();
    }

    @Then("sets the commited usage to 1 year")
    public void setCommitedUsageTo1Year() {
        pricingCalculatorPage
                .switchToInsideFrame()
                .setCommitedUsage(CommitedUsage.ONE_YEAR)
                .switchToOutsideFrame();
    }

    @Then("clicks add to estimate button")
    public void clickAddToEstimateButton() {
        estimationResultBar = pricingCalculatorPage
                .switchToInsideFrame()
                .clickAddToEstimates()
                .waitUntilResultsAppear();
    }

    @Then("stores the calculated total price")
    public void storeCalculatedTotalPrice() {
        expectedMonthlyCost = estimationResultBar.getTotalPrice();
    }

    @Then("clicks on email estimation button")
    public void clickEmailEstimationButton() {
        emailEstimateForm = estimationResultBar.clickEmailEstimate().waitUntilFormAppear();
    }

    @Then("opens a new tab and navigates to email generation page")
    public void openNewTabAndNavigatesToEmailGenerationPage() {
        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
        tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));

        emailGeneratePage = new EmailGeneratePage()
                .open()
                .waintUntilPageLoads();
    }

    @Then("copies the generated email")
    public void copyGeneratedEmail() {
        emailGeneratePage.copyEmail();
    }

    @Then("switches to calculator tab")
    public void switchToCalculatorTab() {
        getDriver().switchTo().window(tabs.get(0));
    }

    @Then("pasts the email into the field")
    public void pastEmailIntoField() {
        pricingCalculatorPage.switchToInsideFrame();
        emailEstimateForm
                .setEmail(Keys.CONTROL + "v");
    }

    @Then("clicks on send email button")
    public void clickSendEmailButton() {
        emailEstimateForm.clickSendEmail();
    }

    @Then("switches to email tab")
    public void switchToEmailTab() {
        getDriver().switchTo().window(tabs.get(1));
    }

    @Then("opens the received email")
    public void openReceivedEmail() {
        emailGeneratePage
                .openReceivedMail();
    }

    @Then("compares the monthly cost with stored total price")
    public void compareMonthlyCostWithStoredTotalPrice() {
        Assert.assertEquals(emailGeneratePage.getMonthlyCost(), expectedMonthlyCost);
    }
}
