package pl.t_mobile.config;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestContext {
    private static TestContext instance = new TestContext();
    private final Map<String, String> context;

    private TestContext() {
        context = new HashMap<>();
    }

    public static TestContext getTestContext() {
        if (instance == null) {
            instance = new TestContext();
        }
        return instance;
    }

    public void setValueInContext(String key, String value) {
        context.put(key, value);
    }

    public String getValueFromContext(String key) {
        return context.get(key);
    }

    public void clear() {
        context.clear();
        log.info("Clear context");
    }
}
