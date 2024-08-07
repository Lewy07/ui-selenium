package pl.t_mobile.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import pl.t_mobile.driver.DriverProvider;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Properties;

@Slf4j
public class Config {

    private static Config instance = new Config();
    private static final String PROPERTIES_FILE = "/config.properties";
    private final Properties properties;
    private WebDriver driver;

    private Config() {
        properties = new Properties();

        try {
            InputStream inputStream = this.getClass().getResourceAsStream(PROPERTIES_FILE);
            assert inputStream != null;
            InputStreamReader isr = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            properties.load(isr);
        } catch (Exception exception) {
            log.error("Can't load properties file: " + PROPERTIES_FILE, exception);
        }

        initializeConfig();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void initializeConfig() {
        var browser = getProperties("browser");
        driver = DriverProvider.getDriverFactory(browser).initializeDriver();
        manageDriver();
        driver.get(getUrl());
    }

    private void manageDriver() {
        driver.manage().window().maximize();
    }

    public String getUrl() {
        var url = properties.getProperty("url");
        log.info("Get basic url: {}", url);

        return url;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initializeConfig();
        }
        return driver;
    }


    public void closeDriver() {
        driver.quit();
        driver = null;
    }

    public String getProperties(String propertyName) {
        return properties.getProperty(propertyName);
    }

}
