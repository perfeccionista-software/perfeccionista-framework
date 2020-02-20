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
public interface PageFactoryServiceConfiguration extends ServiceConfiguration {

    /**
     * name
     * driverInstance
     * timeouts
     * @return
     */
    Map<String, DriverInstanceConfiguration> driverConfigurations();

    boolean closeBrowsersAfterTest();



    Set<String> pagePackages();

    default Class<? extends ElementsConfiguration> elementsConfiguration() {
        return ElementsConfiguration.class;
    }

}
