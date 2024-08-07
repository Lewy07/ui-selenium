package pl.t_mobile.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import pl.t_mobile.pages.MainPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.t_mobile.config.TestContext.getTestContext;
import static pl.t_mobile.utils.WebElementUtil.isElementDisplayed;

@Slf4j
public class MainPageSteps {
    private MainPage mainPage;

    @Given("User open page")
    public void userOpenPage() {
        mainPage = new MainPage();
        log.info("User open main page");
        mainPage.acceptCookie();
    }

    @When("User pick {string} form main navigation")
    public void userPickFormMainNavigation(String mainNavigationOption) {
        mainPage.hoverMainNavigationOption(mainNavigationOption);
        getTestContext().setValueInContext("navOption", mainNavigationOption);
    }

    @SneakyThrows
    @And("User click {string} option in {string} category")
    public void userClickOptionInCategory(String option, String category) {
        mainPage.chooseOptionInSubMenu(getTestContext().getValueFromContext("navOption"), category, option);
        log.info("User click option {} in category {}", option, category);
    }

    @Then("User check count of elements in bucket is equal {int}")
    public void userCheckCountOfElementsInBucketIsEqual(int cartItemsCount) {
        assertTrue("Check cart is displayed", isElementDisplayed(mainPage.getCart()));
        assertEquals("Check elements in cart count", cartItemsCount, mainPage.getItemsInCartCount());
    }
}
