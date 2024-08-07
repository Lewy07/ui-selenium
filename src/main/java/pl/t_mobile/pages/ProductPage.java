package pl.t_mobile.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pl.t_mobile.config.Browser.openPage;
import static pl.t_mobile.utils.WebElementUtil.clickOnElement;
import static pl.t_mobile.utils.WebElementUtil.getElementText;

@Getter
@Setter
public class ProductPage {

    @FindBy(css = "[data-qa='PRD_ProductName']")
    private WebElement productName;
    @FindBy(css = ".vertical_view div[data-qa='PRD_TotalUpfront'] .dt_price_change")
    private WebElement startPrice;
    @FindBy(css = ".vertical_view .rightAlignment .dt_price_change")
    private WebElement monthlyPrice;
    @FindBy(css = ".vertical_view button[data-qa='PRD_AddToBasket']")
    private WebElement addToCart;

    public ProductPage() {
        openPage(this);
    }

    public String getProductName() {
        return getElementText(productName);
    }

    public String getStartPriceAsText() {
        return getElementText(startPrice);
    }

    public String getMonthlyPriceAsText() {
        return getElementText(monthlyPrice);
    }

    public void addProductToCart() {
        clickOnElement(addToCart);
    }
}
