package features;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/", plugin = {"pretty"}
)
public class TestRunner {
}