import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.TestListener;
import utils.WebDriverHelper;

import java.net.MalformedURLException;

@Listeners(TestListener.class)
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setupDriver() throws MalformedURLException {
        driver = WebDriverHelper.getNewDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
