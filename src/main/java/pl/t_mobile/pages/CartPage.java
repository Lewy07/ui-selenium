package pl.t_mobile.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static pl.t_mobile.config.Browser.openPage;
import static pl.t_mobile.utils.WebElementUtil.clickOnElement;
import static pl.t_mobile.utils.WebElementUtil.getElementText;

@Getter
@Setter
public class CartPage {

    @FindBy(css = "[data-qa^='LST_ProductCard']")
    private List<WebElement> items;
    @FindBy(css = "[data-qa='BKT_TotalupFrontCurrCOde']")
    private WebElement startPrice;
    @FindBy(css = "[data-qa='BKT_TotalMonthlyCurrCOde']")
    private WebElement monthlyPrice;
    @FindBy(css = ".magentaStrip img")
    private WebElement mainLogo;

    public CartPage() {
        openPage(this);
    }

    public String getStartPriceAsText() {
        return getElementText(startPrice);
    }

    public String getMonthlyPriceAsText() {
        return getElementText(monthlyPrice);
    }

    public void clickOnMainLogo() {
        clickOnElement(mainLogo);
    }
}
