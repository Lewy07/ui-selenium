package pl.t_mobile.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static pl.t_mobile.config.Browser.getDriver;
import static pl.t_mobile.utils.WebElementUtil.getElementText;

@Slf4j
public class WaitUtil {
    private static final Duration STANDARD = Duration.ofSeconds(30);
    private static final By LOADER_LOCATOR = By.className("loaderText");

    private WaitUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriverWait standardWait() {
        return new WebDriverWait(getDriver(), STANDARD);
    }

    public static void waitForPageToLoad() {
        ExpectedCondition<Boolean> jsLoad = driver -> {
            assert driver != null;
            try {
                driver.findElement(LOADER_LOCATOR);
                return false;
            } catch (NoSuchElementException e) {
                return (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (document.readyState === 'complete')");
            }
        };

        standardWait().until(jsLoad);
    }

    public static void waitForElementToBeClickable(WebElement element) {
        standardWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementToBeVisible(WebElement element) {
        standardWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement presenceOfElementLocatedByLocator(By locator) {
        return standardWait().until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static int waitForReadCountFromElement(WebElement element) {
        ExpectedCondition<Integer> redNumber = driver -> {
            assert driver != null;
            try {
                return Integer.parseInt(getElementText(element));
            } catch (NumberFormatException e) {
                return null;
            }
        };

        return standardWait().until(redNumber);
    }

    public static void scrollToElement(WebElement element) {
        String scrollElementIntoMiddle = "var viewPortHeight = "
                + "Math.max(document.documentElement.clientHeight, window.innerHeight || 0); "
                + "var elementTop = arguments[0].getBoundingClientRect().top; "
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ExpectedCondition<Boolean> scroll = driver -> {
            assert driver != null;
            ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
            return true;
        };

        standardWait().until(scroll);
    }
}