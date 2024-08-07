package pl.t_mobile.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import pl.t_mobile.pages.CategoryPage;

import static org.junit.Assert.assertTrue;
import static pl.t_mobile.config.TestContext.getTestContext;
import static pl.t_mobile.utils.WebElementUtil.isElementDisplayed;

@Slf4j
public class CategorySteps {
    private CategoryPage categoryPage;

    @Then("User should be ona a page with list of smartwatches")
    public void userShouldBeOnaAPageWithListOfSmartwatches() {
        categoryPage = new CategoryPage();

        for (WebElement item : categoryPage.getItems()) {
            assertTrue("Check smartwatch are displayed", isElementDisplayed(item));
        }
        log.info("User is on the page with list of smartwatches");
    }

    @When("User click first possible element")
    public void userClickFirstPossibleElement() {
        var productName = categoryPage.openProductPage(0);
        getTestContext().setValueInContext("openedProductName", productName);
    }
}
