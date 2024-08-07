package pl.t_mobile.driver;

import org.reflections.Reflections;

import java.util.Set;

public class DriverProvider {

    public static DriverFactory getDriverFactory(String browserType) {
        var driverFactoryPackage = DriverFactory.class.getPackage().getName();
        var reflections = new Reflections(driverFactoryPackage);
        Set<Class<? extends DriverFactory>> driverImplementations = reflections.getSubTypesOf(DriverFactory.class);

        return driverImplementations.stream()
                .filter(driverClass -> driverClass.getSimpleName().equalsIgnoreCase(browserType))
                .findFirst()
                .map(driverClass -> {
                    try {
                        return driverClass.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow(() -> new IllegalStateException("Did not find driver class with name " + browserType));
    }
}
