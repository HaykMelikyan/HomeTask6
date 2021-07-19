import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.TestListener;
import utils.WebDriverHelper;

import java.net.MalformedURLException;

@Listeners(TestListener.class)
public abstract class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setupDriver() throws MalformedURLException {
        driver.set(WebDriverHelper.getDriver());
        driver.get().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.get().quit();
        driver.set(null);
    }

    protected WebDriver getDriver() {
        return driver.get();
    }
}
