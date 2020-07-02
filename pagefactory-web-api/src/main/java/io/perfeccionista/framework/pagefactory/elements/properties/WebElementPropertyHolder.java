package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public class WebElementPropertyHolder {

    private final String name;
    private final WebLocatorHolder locatorHolder;
    private final WebElementPropertyExtractor<WebChildElement> propertyExtractor;

    private WebElementPropertyHolder(String name,
                                     WebLocatorHolder locatorHolder,
                                     WebElementPropertyExtractor<WebChildElement> propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static WebElementPropertyHolder of(String name,
                                              WebLocatorHolder locatorHolder,
                                              WebElementPropertyExtractor<WebChildElement> propertyExtractor) {
        return new WebElementPropertyHolder(name, locatorHolder, propertyExtractor);
    }

    public String getName() {
        return name;
    }

    public WebLocatorHolder getLocatorHolder() {
        return locatorHolder;
    }

    public String extractPropertyValue(WebChildElement element) {
        return propertyExtractor.extract(element, locatorHolder);
    }

    public WebElementPropertyExtractor<WebChildElement> getPropertyExtractor() {
        return propertyExtractor;
    }

}
