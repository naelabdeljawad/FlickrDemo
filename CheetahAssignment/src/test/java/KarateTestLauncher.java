import com.intuit.karate.testng.KarateRunner;
import cucumber.api.CucumberOptions;

@CucumberOptions(features = "features/KarateScenario.feature", plugin = {"pretty", "html:target/cucumber-report/jsonfiles", "json:target/cucumber-report/jsonfiles/example.json"})
public class KarateTestLauncher extends KarateRunner {

}
