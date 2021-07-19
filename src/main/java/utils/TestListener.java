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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    Logger logger = LogManager.getRootLogger();

    @Override
    public void onTestFailure(ITestResult result) {
        logger.warn("Test failed");
        saveScreenshot(result.getName());
    }

    private void saveScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) WebDriverHelper
                    .getDriver())
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File(
                    ".//target/screenshots/"
                            + testName
                            + getCurrentTimeAsString()
                            + ".png"));
            logger.info("Screenshot has been taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
