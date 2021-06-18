import googlecloudpages.*;
import optionpickers.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tenminuteemailpages.EmailGeneratePage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Hardcore {
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
    public void googleCloudPricingCalculatorTest() throws AWTException, InterruptedException {
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

        String expectedMonthlyCost = estimationResultBar.getTotalPrice();
        EmailEstimateForm emailEstimateForm = estimationResultBar
                .clickEmailEstimate()
                .waitUntilFormAppear();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_T);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_T);
        Thread.sleep(1000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(500);

        EmailGeneratePage emailGeneratePage = new EmailGeneratePage(driver)
                .open()
                .waintUntilPageLoads()
                .copyEmail();

        driver.switchTo().window(tabs.get(0));
        Thread.sleep(500);

        pricingCalculatorPage.switchToInsideFrame();
        emailEstimateForm
                .setEmail(Keys.CONTROL + "v")
                .sendEmail();

        driver.switchTo().window(tabs.get(1));
        Thread.sleep(500);

        String actualMonthlyCost = emailGeneratePage
                .openReceivedMail()
                .getMonthlyCost();

        Assert.assertEquals(actualMonthlyCost, expectedMonthlyCost);
    }
}
