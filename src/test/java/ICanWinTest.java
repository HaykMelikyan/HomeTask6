import optionpickers.Expiration;
import org.testng.annotations.Test;
import pastbinpages.HomePage;

public class ICanWinTest extends BaseTest {
    @Test
    public void createNewPaste() {
        new HomePage(getDriver()).open()
                .waitUntilPageLoads()
                .inputCode("Hello from WebDriver")
                .setExpiration(Expiration.TEN_MINUTES)
                .inputName("helloweb")
                .clickCreateNewPaste()
                .waitUntilPageLoads();
    }
}
