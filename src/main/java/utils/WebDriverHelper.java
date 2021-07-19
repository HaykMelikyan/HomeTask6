package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverHelper {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() throws MalformedURLException {
        return driver.get() == null ? getNewDriver() : driver.get();
    }

    private static WebDriver getNewDriver() throws MalformedURLException {
        if (new PropertyReader().getProperty("browser").equals("firefox")) {
            driver.set(createFirefoxDriver());
        } else {
            driver.set(createChromeDriver());
        }
        return driver.get();
    }

    private static WebDriver createChromeDriver() throws MalformedURLException {
        if (isRemote()) {
            return createRemoteDriver(new ChromeOptions());
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

    private static WebDriver createFirefoxDriver() throws MalformedURLException {
        if (isRemote()) {
            return createRemoteDriver(new FirefoxOptions());
        } else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }

    private static boolean isRemote() {
        return new PropertyReader().getProperty("remote").equals("true");
    }

    private static WebDriver createRemoteDriver(Capabilities browserOptions) throws MalformedURLException {
        return new RemoteWebDriver(new URL(new PropertyReader().getProperty("selenium.grid.hub")), browserOptions);
    }
}
