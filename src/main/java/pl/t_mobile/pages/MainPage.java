package pl.t_mobile.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.t_mobile.utils.WaitUtil;

import java.util.List;

import static pl.t_mobile.config.Browser.openPage;
import static pl.t_mobile.utils.WaitUtil.presenceOfElementLocatedByLocator;
import static pl.t_mobile.utils.WaitUtil.waitForReadCountFromElement;
import static pl.t_mobile.utils.WebElementUtil.*;

@Getter
@Setter
public class MainPage {

    @FindBy(id = "didomi-notice-agree-button")
    private WebElement cookieAcceptance;
    @FindBy(className = "menu-dropdown-item")
    private List<WebElement> mainNavigationOptions;
    @FindBy(css = "#main-menu ~ div [data-ga-ea='basket']")
    private WebElement cart;
    @FindBy(css = "#main-menu ~ div .rounded-full")
    private WebElement cartItemsCount;

    public MainPage() {
        openPage(this);
    }

    public void acceptCookie() {
        clickOnElement(cookieAcceptance);
    }

    public void hoverMainNavigationOption(String option) {
        var elementToHover = getElementFromListContainsText(mainNavigationOptions, option);
        hoverOnElement(elementToHover);
    }

    public void chooseOptionInSubMenu(String navOption, String category, String option) {
        var attributeValue = String.join("/", navOption, category, option);
        var subMenuElement = presenceOfElementLocatedByLocator(By
                .cssSelector("a[data-ga-ea='nav-links - " + attributeValue + "'] span"));
        clickOnElement(subMenuElement);
    }

    public int getItemsInCartCount() {
        return waitForReadCountFromElement(cartItemsCount);
    }
}
