package pl.t_mobile.pages;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static pl.t_mobile.config.Browser.openPage;
import static pl.t_mobile.utils.WebElementUtil.*;

@Getter
@Setter
public class CategoryPage {

    @FindBy(css = "[data-qa^='LST_ProductCard']")
    private List<WebElement> items;
    @FindBy(css = ".mainHeading")
    private List<WebElement> itemsName;

    public CategoryPage() {
        openPage(this);
    }

    public String openProductPage(int index) {
        var productName = getElementText(itemsName.get(index));
        clickOnElement(items.get(index));

        return productName;
    }
}
