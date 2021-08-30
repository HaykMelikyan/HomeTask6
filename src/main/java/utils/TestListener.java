package utils;

import com.epam.ta.reportportal.log4j.appender.ReportPortalAppender;
import com.epam.ta.reportportal.log4j.appender.ReportPortalLog4j2Appender;
import org.apache.logging.log4j.LogManager;
import org.slf4j.event.LoggingEvent;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        LogManager.getRootLogger().warn("Test failed");
        ScreenshotMaker.saveScreenshot(result.getName());
    }
}
