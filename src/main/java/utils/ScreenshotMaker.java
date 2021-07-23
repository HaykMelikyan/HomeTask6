package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.drivers.WebDriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotMaker {

    private ScreenshotMaker(){}

    public static void saveScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) WebDriverFactory
                    .getDriver())
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File(
                    ".//target/screenshots/"
                            + testName
                            + getCurrentTimeAsString()
                            + ".png"));
            LogManager.getRootLogger().info("Screenshot has been taken");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
