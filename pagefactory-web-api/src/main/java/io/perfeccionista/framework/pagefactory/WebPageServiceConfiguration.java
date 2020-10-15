package io.perfeccionista.framework.pagefactory;

import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.service.ServiceConfiguration;

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

    Set<String> getPageObjectPackages();

    WebPageFactoryPreferences getElementsPreferences();

}
