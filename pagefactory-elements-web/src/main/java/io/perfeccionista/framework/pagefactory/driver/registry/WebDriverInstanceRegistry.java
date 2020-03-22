package io.perfeccionista.framework.pagefactory.driver.registry;

import io.perfeccionista.framework.pagefactory.driver.WebDriverInstance;
import io.perfeccionista.framework.pagefactory.driver.WebDriverInstanceConfiguration;

import java.util.Map;

public class WebDriverInstanceRegistry {

    private Map<String, WebDriverInstanceConfiguration> webDriverInstanceConfigurations;

    public WebDriverInstanceRegistry(Map<String, WebDriverInstanceConfiguration> webDriverInstanceConfigurations) {
        this.webDriverInstanceConfigurations = webDriverInstanceConfigurations;
    }

    public void init() {

    }

    public WebDriverInstance getDefault() {

        return null;
    }

    public WebDriverInstance getByName(String driverInstanceName) {

        return null;
    }

}
