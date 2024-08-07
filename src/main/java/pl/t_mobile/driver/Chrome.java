package pl.t_mobile.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class Chrome implements DriverFactory {

    @Override
    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions(chromePrefs()));
    }

    protected static HashMap<String, Object> chromePrefs() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("profile.managed_default_content_settings.notifications", 1);
        chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        return chromePrefs;
    }

    protected static ChromeOptions chromeOptions(HashMap<String, Object> chromePrefs) {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("window-size=1920x1080");
        options.addArguments("start-maximized");

        return options;
    }
}
