package utils.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.PropertyReader;

import java.net.MalformedURLException;
import java.net.URL;

abstract class DriverProvider {
    public abstract WebDriver createDriver();

    protected boolean isRemote() {
        return new PropertyReader().getProperty("remote").equals("true");
    }

    protected WebDriver createRemoteDriver(Capabilities browserOptions){
        try {
            return new RemoteWebDriver(new URL(new PropertyReader().getProperty("selenium.grid.hub")), browserOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
