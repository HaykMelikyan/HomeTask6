package testclasses;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.TestListener;
import utils.drivers.WebDriverFactory;

@Listeners({TestListener.class, ReportPortalTestNGListener.class})
public abstract class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setupDriver() {
        driver.set(WebDriverFactory.getDriver());
        getDriver().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        getDriver().quit();
        driver.set(null);
        WebDriverFactory.removeDriver();
    }

    protected WebDriver getDriver() {
        return driver.get();
    }
}
