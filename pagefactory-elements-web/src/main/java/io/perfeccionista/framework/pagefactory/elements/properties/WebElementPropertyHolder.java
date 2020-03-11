package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;

public class WebElementPropertyHolder implements ElementPropertyHolder {

    private final String name;
    private final LocatorHolder locatorHolder;
    private final Class<? extends ElementPropertyExtractor<WebChildElement>> propertyExtractor;

    private WebElementPropertyHolder(String name,
                                        LocatorHolder locatorHolder,
                                        Class<? extends ElementPropertyExtractor<WebChildElement>> propertyExtractor) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.propertyExtractor = propertyExtractor;
    }

    public static WebElementPropertyHolder of(String name,
                                             LocatorHolder locatorHolder,
                                             Class<? extends ElementPropertyExtractor<WebChildElement>> jsFunction) {
        return new WebElementPropertyHolder(name, locatorHolder, jsFunction);
    }

    public String getName() {
        return name;
    }

    public LocatorHolder getLocatorHolder() {
        return locatorHolder;
    }

    public Class<? extends ElementPropertyExtractor<WebChildElement>> getPropertyExtractor() {
        return propertyExtractor;
    }

    public String toString() {
        return "ElementPropertyHolder {name = " + name + ", locator = " + locatorHolder.toString() + ", jsFunctionClass = " + propertyExtractor.getCanonicalName() + "}";
    }

}
