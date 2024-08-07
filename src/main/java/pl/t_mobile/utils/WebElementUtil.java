package pl.t_mobile.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static pl.t_mobile.config.Browser.getDriver;
import static pl.t_mobile.utils.WaitUtil.*;

@Slf4j
public class WebElementUtil {

    private WebElementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Actions getActions() {
        return new Actions(getDriver());
    }

    public static String getElementName(WebElement element) {
        try {
            var text = element.getText();

            return text.isBlank() ? "Element with tag name " + element.getTagName() : text;
        } catch (Exception exception) {
            log.error("Error while getting element name: {}", element, exception);
            return null;
        }
    }

    public static void clickOnElement(WebElement elementToClick) {
        waitForElementToBeClickable(elementToClick);
        scrollToElement(elementToClick);

        try {
            var elementName = getElementName(elementToClick);
            elementToClick.click();
            log.info("Element clicked: {}", elementName);
        } catch (Exception exception) {
            log.error("Error while clicking on element: {}", elementToClick, exception);
        }

        waitForPageToLoad();
    }

    public static String getElementText(WebElement elementToExtractText) {
        var elementText = elementToExtractText.getText();
        log.info("Get text {} from element", elementText);

        return elementText;
    }

    public static WebElement getElementFromListContainsText(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            if (getElementText(element).contains(text)) {
                log.info("Found element contains text {}", text);
                return element;
            }
        }

        return null;
    }

    public static void hoverOnElement(WebElement elementToHover) {
        waitForElementToBeVisible(elementToHover);
        scrollToElement(elementToHover);

        try {
            var elementName = getElementName(elementToHover);
            getActions().moveToElement(elementToHover).perform();
            log.info("Element hover element: {}", elementName);
        } catch (Exception exception) {
            log.error("Error while hover element: {}", elementToHover, exception);
        }
    }

    public static boolean isElementDisplayed(WebElement elementToCheck) {
        return elementToCheck.isDisplayed();
    }
}