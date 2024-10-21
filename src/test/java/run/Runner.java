package run;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/java/features/Task.feature",  // Path to your feature file
    glue = "steps",  // Package where step definitions are located
    plugin = {
        "html:target/cucumber-reports.html"    }
)
public class Runner extends AbstractTestNGCucumberTests {
}
