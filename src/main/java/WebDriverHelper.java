import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverHelper {
    public static WebDriver getChromeDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://10.22.221.72:4444/wd/hub"), new ChromeOptions());
    }

    public static WebDriver getFirefoxDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://10.22.221.72:4444/wd/hub"), new FirefoxOptions());
    }
}
