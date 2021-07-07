import optionpickers.Expiration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pastbinpages.HomePage;

import java.net.MalformedURLException;

public class ICanWinTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver() throws MalformedURLException {
        driver = WebDriverHelper.getFirefoxDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void createNewPaste() {
        new HomePage(driver).open()
                .waitUntilPageLoads()
                .inputCode("Hello from WebDriver")
                .setExpiration(Expiration.TEN_MINUTES)
                .inputName("helloweb")
                .clickCreateNewPaste()
                .waitUntilPageLoads();
    }
}
