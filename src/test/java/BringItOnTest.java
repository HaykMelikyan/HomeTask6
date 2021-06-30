import optionpickers.Expiration;
import optionpickers.Highlighting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pastbinpages.HomePage;
import pastbinpages.PasteCreatedPage;

public class BringItOnTest {
    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void createNewPasteTest() {
        String expectedCode = "git config --global user.name  \"New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "git push origin master --force";
        String name = "how to gain dominance among developers";
        String expectedName = "how to gain dominance among developers - Pastebin.com";

        HomePage homePage = new HomePage(driver);
        PasteCreatedPage pasteCreatedPage = homePage.open()
                .waitUntilPageLoads()
                .inputCode(expectedCode)
                .setHighlighting(Highlighting.BASH)
                .setExpiration(Expiration.TEN_MINUTES)
                .inputName(name)
                .clickCreateNewPaste()
                .waitUntilPageLoads();

        String actualCode = pasteCreatedPage.getCode();
        String actualTitle = pasteCreatedPage.getPageTitle();
        String actualHighlighting = pasteCreatedPage.getHighlighting();

        Assert.assertEquals(actualTitle, expectedName);
        Assert.assertEquals(actualHighlighting, Highlighting.BASH.getOptionText());
        Assert.assertEquals(actualCode, expectedCode);
    }
}
