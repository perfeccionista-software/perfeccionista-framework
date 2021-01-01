package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.pagefactory.elements.preferences.DefaultSeleniumWebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.elements.preferences.WebPageFactoryPreferences;
import io.perfeccionista.framework.pagefactory.WebPageServiceConfiguration;

import java.util.Set;

public class TestSeleniumWebPageServiceConfiguration implements WebPageServiceConfiguration {

    @Override
    public Set<String> getPageObjectPackages() {
        return Set.of("io.perfeccionista.framework.pagefactory.pageobjects");
    }

    @Override
    public WebPageFactoryPreferences getElementsPreferences() {
        return new DefaultSeleniumWebPageFactoryPreferences();
    }

}
