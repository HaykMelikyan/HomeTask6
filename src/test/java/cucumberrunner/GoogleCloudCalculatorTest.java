package cucumberrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = "cucumbersteps",
        features = "src/test/java/cucumberrunner/GoogleCloudCalculatorTest.java"
)
public class GoogleCloudCalculatorTest extends AbstractTestNGCucumberTests {
}
