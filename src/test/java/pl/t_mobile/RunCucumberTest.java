package pl.t_mobile;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = "src/test/java/pl/t_mobile/features",
        glue = "pl/t_mobile/steps")
public class RunCucumberTest {
}