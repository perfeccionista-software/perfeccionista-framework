package io.perfeccionista.framework.pagefactory.driver;

import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;

public interface WebDriverServiceConfiguration extends ServiceConfiguration {

    /**
     * name
     * driverInstance
     * timeouts
     * @return
     */
    Map<String, WebDriverInstanceConfiguration> getWebDriverInstanceConfigurations();

    default boolean isCloseBrowsersAfterTest() {
        return true;
    }

}
