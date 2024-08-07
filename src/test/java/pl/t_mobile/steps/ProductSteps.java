package pl.t_mobile.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import pl.t_mobile.pages.ProductPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pl.t_mobile.config.TestContext.getTestContext;
import static pl.t_mobile.utils.WebElementUtil.isElementDisplayed;

@Slf4j
public class ProductSteps {
    private ProductPage productPage;

    @Then("User should be ona a page with details of the product")
    public void userShouldBeOnaAPageWithDetailsOfTheProduct() {
        productPage = new ProductPage();

        assertEquals("Check user is on the product page",
                getTestContext().getValueFromContext("openedProductName"),
                productPage.getProductName());
        log.info("User is on the product page");
    }

    @And("User see prices")
    public void userSeePrices() {
        assertTrue("Check start price", isElementDisplayed(productPage.getStartPrice()));
        assertTrue("Check monthly", isElementDisplayed(productPage.getMonthlyPrice()));

        getTestContext().setValueInContext("startPrice", productPage.getStartPriceAsText());
        getTestContext().setValueInContext("monthlyPrice", productPage.getMonthlyPriceAsText());
    }

    @When("User add product to the cart")
    public void userAddProductToTheCart() {
        productPage.addProductToCart();
    }
}
