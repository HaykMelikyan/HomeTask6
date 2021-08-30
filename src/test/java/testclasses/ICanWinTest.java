package testclasses;

import optionpickers.Expiration;
import org.testng.annotations.Test;
import pastbinpages.HomePage;

public class ICanWinTest extends BaseTest {
    @Test(description = "Create New Paste In Pastbin")
    public void createNewPaste() {
        new HomePage().open()
                .waitUntilPageLoads()
                .inputCode("Hello from WebDriver")
                .setExpiration(Expiration.TEN_MINUTES)
                .inputName("helloweb")
                .clickCreateNewPaste()
                .waitUntilPageLoads();
    }
}
