package io.perfeccionista.framework.pagefactory.configurations;

import io.perfeccionista.framework.pagefactory.DefaultSeleniumWebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.WebElementsConfiguration;
import io.perfeccionista.framework.pagefactory.WebPageServiceConfiguration;
import io.perfeccionista.framework.pagefactory.elements.WebPage;
import io.perfeccionista.framework.pagefactory.elements.AbstractWebPage;
import io.perfeccionista.framework.pagefactory.elements.base.Element;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestWebPageServiceConfiguration implements WebPageServiceConfiguration {

    @Override
    public Set<String> getPageObjectPackages() {
        return Set.of("io.perfeccionista.framework.pagefactory.pageobjects");
    }

    @Override
    public WebElementsConfiguration getElementsConfiguration() {
        return new DefaultSeleniumWebElementsConfiguration();
    }
}
