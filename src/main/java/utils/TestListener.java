package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        Logger logger = LogManager.getRootLogger();
        logger.warn("Test failed");
        try {
            File screenshot = ((TakesScreenshot) WebDriverHelper
                    .getCurrentDriver())
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File(
                    ".//target/screenshots/"
                            + result.getName()
                            + System.currentTimeMillis()
                            + ".png"));
            logger.info("Screenshot has been taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getCurrentDateTime() {

    }
}
