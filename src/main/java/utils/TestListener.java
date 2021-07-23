package utils;

import org.apache.logging.log4j.LogManager;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        LogManager.getRootLogger().warn("Test failed");
        ScreenshotMaker.saveScreenshot(result.getName());
    }
}
