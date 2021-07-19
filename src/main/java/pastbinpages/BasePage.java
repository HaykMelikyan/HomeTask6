package pastbinpages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected static final String BASE_URL = "https://pastebin.com";

    protected abstract BasePage waitUntilPageLoads();
}
