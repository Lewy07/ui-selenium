package pl.t_mobile.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import pl.t_mobile.pages.CartPage;

import static org.junit.Assert.assertEquals;
import static pl.t_mobile.config.Browser.getDriver;
import static pl.t_mobile.config.TestContext.getTestContext;

@Slf4j
public class CartSteps {
    private static final String CART_URL = "https://www.t-mobile.pl/sklep/basket";
    private CartPage cartPage;

    @Then("User should be ona a page with cart")
    public void userShouldBeOnaAPageWithCart() {
        cartPage = new CartPage();
        assertEquals("Check cart URL", CART_URL, getDriver().getCurrentUrl());
        log.info("User is in the cart page");
    }

    @And("User check price")
    public void userCheckPrice() {
        assertEquals("Check Start Price", getTestContext().getValueFromContext("startPrice"),
                cartPage.getStartPriceAsText());
        assertEquals("Check Monthly Price", getTestContext().getValueFromContext("monthlyPrice"),
                cartPage.getMonthlyPriceAsText());
        log.info("User checked prices in cart");
    }

    @When("User go to the main page")
    public void userGoToTheMainPage() {
        cartPage.clickOnMainLogo();
        log.info("User go to the main page by clicking on the logo");
    }
}
