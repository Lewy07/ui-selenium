package pl.t_mobile.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static pl.t_mobile.utils.WaitUtil.waitForPageToLoad;

public class Browser {

    private Browser() {
        throw new IllegalStateException("Utility class");
    }

    public static WebDriver getDriver() {
        return Config.getInstance().getDriver();
    }

    public static void tearDown() {
        Config.getInstance().closeDriver();
    }

    public static <T> void openPage(T object) {
        waitForPageToLoad();
        PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), object);
    }
}
