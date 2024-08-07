package pl.t_mobile.driver;

import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox implements DriverFactory {

    @Override
    public WebDriver initializeDriver() {
        FirefoxDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(prepareFirefoxOption());
    }

    protected static FirefoxOptions prepareFirefoxOption() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--disable-popup-blocking");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--disable-gpu");
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--start-maximized");

        return firefoxOptions;
    }
}
