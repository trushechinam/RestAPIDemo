package cucumver.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefenitions",
        plugin = "json:target/jsonReports/cucumber-report.json",
        tags = "@DeletePlace, @AddPlace"
)

public class TestRunnerTest {

}
