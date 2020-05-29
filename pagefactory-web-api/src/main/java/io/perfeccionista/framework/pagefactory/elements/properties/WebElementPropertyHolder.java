package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public class WebElementPropertyHolder {

    private final String name;
    private final WebLocatorHolder locatorHolder;
    private final Class<? extends WebElementPropertyExtractor<WebChildElement>> propertyExtractor;

    private WebElementPropertyHolder(String name,
                                        WebLocatorHolder locatorHolder,
                                        Class<? extends WebElementPropertyExtractor<WebChildElement>> propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static WebElementPropertyHolder of(String name,
                                             WebLocatorHolder locatorHolder,
                                             Class<? extends WebElementPropertyExtractor<WebChildElement>> jsFunction) {
        return new WebElementPropertyHolder(name, locatorHolder, jsFunction);
    }

    public String getName() {
        return name;
    }

    public WebLocatorHolder getLocatorHolder() {
        return locatorHolder;
    }

    public Class<? extends WebElementPropertyExtractor<WebChildElement>> getPropertyExtractor() {
        return propertyExtractor;
    }

    public String toString() {
        return "ElementPropertyHolder {name = " + name + ", locator = " + locatorHolder.toString() + ", jsFunctionClass = " + propertyExtractor.getCanonicalName() + "}";
    }

}
