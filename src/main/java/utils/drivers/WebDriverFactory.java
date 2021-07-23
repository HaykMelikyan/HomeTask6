package utils.drivers;

import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverFactory(){}

    public static WebDriver getDriver() {
        return driver.get() == null ? getNewDriver() : driver.get();
    }

    private static WebDriver getNewDriver() {
        DriverProvider driverProvider;
        if (new PropertyReader().getProperty("browser").equals("firefox")) {
            driverProvider = new FirefoxDriverProvider();
        } else {
            driverProvider = new ChromeDriverProvider();
        }
        driver.set(driverProvider.createDriver());
        return driver.get();
    }
}
