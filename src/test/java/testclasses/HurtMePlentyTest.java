package testclasses;

import googlecloudpages.EstimationResultBar;
import googlecloudpages.HomePage;
import googlecloudpages.PricingCalculatorPage;
import googlecloudpages.SearchResultsPage;
import optionpickers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HurtMePlentyTest extends BaseTest {
    @Test
    public void googleCloudPricingCalculatorTest() {
        SearchResultsPage searchResultsPage = new HomePage()
                .open()
                .waitUntilPageLoads()
                .clickSearchButton()
                .searchText("Google Cloud Platform Pricing Calculator");

        PricingCalculatorPage pricingCalculatorPage = searchResultsPage
                .waitUntilPageLoads()
                .clickFirstResult();

        EstimationResultBar estimationResultBar = pricingCalculatorPage
                .waitUntilPageLoads()
                .switchToInsideFrame()
                .inputNumberOfInstances(4)
                .setMachineType(MachineType.E2_STANDARD_8)
                .inputNumberOfNodes(1)
                .clickAddGpuCheckbox()
                .setNumberOfGpus(NumberOfGpus.FOUR)
                .setGpuType(GpuType.NVIDIA_TESLA_V100)
                .setLocalSsd(LocalSsd.STORAGE_24X375GB)
                .setDatacenterLocation(DatacenterLocation.FRANKFURT)
                .setCommitedUsage(CommitedUsage.ONE_YEAR)
                .clickAddToEstimates()
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
