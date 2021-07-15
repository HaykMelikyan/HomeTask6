package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverHelper {
    private static WebDriver driver;

    public static WebDriver getNewDriver() throws MalformedURLException {
        if (System.getProperty("browser", "chrome").equals("firefox")) {
            driver = createFirefoxDriver();
        } else {
            driver = createChromeDriver();
        }
        return driver;
    }

    public static WebDriver getCurrentDriver() throws MalformedURLException {
        return driver == null ? getNewDriver() : driver;
    }

    private static WebDriver createChromeDriver() throws MalformedURLException {
        if (System.getProperty("remoteExecution", "no").equals("yes")) {
            return new RemoteWebDriver(new URL("http://10.22.221.72:4444/wd/hub"), new ChromeOptions());
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

    private static WebDriver createFirefoxDriver() throws MalformedURLException {
        if (System.getProperty("remoteExecution", "no").equals("yes")) {
            return new RemoteWebDriver(new URL("http://10.22.221.72:4444/wd/hub"), new FirefoxOptions());
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }
}
