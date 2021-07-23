import googlecloudpages.*;
import optionpickers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import tenminuteemailpages.EmailGeneratePage;
import utils.PropertyReader;

import java.util.ArrayList;

public class HardcoreTest extends BaseTest {
    @Test
    public void googleCloudPricingCalculatorTest() {
        PropertyReader prop = new PropertyReader();
        Logger logger = LogManager.getRootLogger();

        logger.trace("Test started");
        SearchResultsPage searchResultsPage = new HomePage()
                .open()
                .waitUntilPageLoads()
                .searchText(prop.getProperty("googlecloud.search"));
        logger.trace("Performed search for pricing calculator");

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .waitUntilPageLoads()
                .clickFirstResult();
        logger.trace("Opened pricing calculator page");

        EstimationResultBar estimationResultBar = pricingCalculatorPage
                .waitUntilPageLoads()
                .switchToInsideFrame()
                .inputNumberOfInstances(
                        Integer.parseInt(
                                prop.getProperty("form.instances")))
                .setMachineType(MachineType.E2_STANDARD_8)
                .inputNumberOfNodes(
                        Integer.parseInt(
                                prop.getProperty("form.nodes")))
                .clickAddGpuCheckbox()
                .setNumberOfGpus(NumberOfGpus.FOUR)
                .setGpuType(GpuType.NVIDIA_TESLA_V100)
                .setLocalSsd(LocalSsd.STORAGE_24X375GB)
                .setDatacenterLocation(DatacenterLocation.FRANKFURT)
                .setCommitedUsage(CommitedUsage.ONE_YEAR)
                .clickAddToEstimates()
                .waitUntilResultsAppear();
        logger.trace("Filled the form");

        String expectedMonthlyCost = estimationResultBar.getTotalPrice();
        logger.trace("Stored the total price");
        EmailEstimateForm emailEstimateForm = estimationResultBar
                .clickEmailEstimate()
                .waitUntilFormAppear();
        logger.trace("Opened email estimate form");

        ((JavascriptExecutor) getDriver()).executeScript("window.open()");
        logger.trace("Opened new tab in browser");

        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        logger.trace("Switched to the new tab");

        EmailGeneratePage emailGeneratePage = new EmailGeneratePage()
                .open()
                .waintUntilPageLoads()
                .copyEmail();
        logger.trace("Opened the website and copied the generated email");

        getDriver().switchTo().window(tabs.get(0));
        logger.trace("Switched back to the calculator tab");

        pricingCalculatorPage.switchToInsideFrame();
        emailEstimateForm
                .setEmail(Keys.CONTROL + "v")
                .clickSendEmail();
        logger.trace("Pasted the email in the field and clicked send");

        getDriver().switchTo().window(tabs.get(1));
        logger.trace("Switched to email tab");

        String actualMonthlyCost = emailGeneratePage
                .openReceivedMail()
                .getMonthlyCost();
        logger.trace("Stored the price from the received mail");

        logger.trace("Performing assertion");
        Assert.assertEquals(actualMonthlyCost, expectedMonthlyCost);
        logger.trace("Test passed");
    }
}
