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
public interface WebPageServiceConfiguration extends ServiceConfiguration {

    Set<String> getPagePackages();



    default WebElementsConfiguration getElementsConfiguration() {
        return new DefaultWebElementsConfiguration();
    }

}
