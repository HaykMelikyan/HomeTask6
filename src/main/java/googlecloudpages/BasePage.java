package googlecloudpages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected final String BASE_URL = "https://cloud.google.com/";

    protected abstract BasePage waitUntilPageLoads();
}
