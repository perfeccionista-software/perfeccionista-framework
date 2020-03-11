package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.driver.DriverInstanceConfiguration;
import io.perfeccionista.framework.service.ServiceConfiguration;

import java.util.Map;
import java.util.Set;

/**
 * pagePackages
 * screenshotWriter
 * outerHtmlWriter
 * elementsConfiguration
 * coverageFactory
 * @return
 */
public interface WebPageFactoryServiceConfiguration extends ServiceConfiguration {

    Set<String> getPagePackages();

    /**
     * name
     * driverInstance
     * timeouts
     * @return
     */
    Map<String, DriverInstanceConfiguration> getDriverConfigurations();

    default boolean isCloseBrowsersAfterTest() {
        return true;
    }

    default WebElementsConfiguration getElementsConfiguration() {
        return new DefaultWebElementsConfiguration();
    }

}
