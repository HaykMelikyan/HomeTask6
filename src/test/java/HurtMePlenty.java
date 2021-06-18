import googlecloudpages.EstimationResultBar;
import googlecloudpages.HomePage;
import googlecloudpages.PricingCalculatorPage;
import googlecloudpages.SearchResultsPage;
import optionpickers.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HurtMePlenty {
    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void googleCloudPricingCalculatorTest() {
        SearchResultsPage searchResultsPage = new HomePage(driver)
                .open()
                .waitUntilPageLoads()
                .searchText("Google Cloud Platform Pricing Calculator");

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .waitUntilPageLoads()
                .clickNeededResult();

        EstimationResultBar estimationResultBar = pricingCalculatorPage
                .waitUntilPageLoads()
                .switchToInsideFrame()
                .setNumberOfInstances(4)
                .setMachineType(MachineType.E2_STANDARD_8)
                .setNumberOfNodes(1)
                .clickAddGpuCheckbox()
                .setNumberOfGpus(NumberOfGpus._4)
                .setGpuType(GpuType.NVIDIA_TESLA_V100)
                .setLocalSsd(LocalSsd._24x375GB)
                .setDatacenterLocation(DatacenterLocation.FRANKFURT)
                .setCommitedUsage(CommitedUsage.ONE_YEAR)
                .clickAddToEstimate()
                .waitUntilResultsAppear();

        String actualVmClass = estimationResultBar.getVmClass();
        String actualInstanceType = estimationResultBar.getInstanceType();
        String actualRegion = estimationResultBar.getRegion();
        String actualCommitmentTerm = estimationResultBar.getCommitmentTerm();
        String actualTotalPrice = estimationResultBar.getTotalPrice();

        Assert.assertEquals(actualVmClass, "regular");
        Assert.assertEquals(actualInstanceType, "e2-standard-8");
        Assert.assertEquals(actualRegion, "Frankfurt");
        Assert.assertEquals(actualCommitmentTerm, "1 Year");
        Assert.assertEquals(actualTotalPrice, "4,517.82");
    }
}
