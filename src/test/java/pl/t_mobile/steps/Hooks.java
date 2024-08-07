package pl.t_mobile.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;

import static pl.t_mobile.config.Browser.getDriver;
import static pl.t_mobile.config.Browser.tearDown;
import static pl.t_mobile.config.TestContext.getTestContext;


@Slf4j
public class Hooks {

    @Before
    public void setUpBeforeTest(Scenario scenario) {
        log.info("Starting scenario: {}", scenario.getName());
        getDriver();
    }

    @After
    public void tearDownAfterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            log.error("Scenario failed: {}", scenario.getName());
        }
        tearDown();
        getTestContext().clear();
        log.info("Tear down");
    }
}
