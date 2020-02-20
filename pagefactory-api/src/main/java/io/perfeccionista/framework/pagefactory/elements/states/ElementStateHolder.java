package io.perfeccionista.framework.pagefactory.elements.states;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;

/**
 * TODO JavaDoc
 */
public class ElementStateHolder {

    private final String name;
    private final LocatorHolder locatorHolder;

    private ElementStateHolder(String name, LocatorHolder locatorHolder) {
        this.name = name;
        this.locatorHolder = locatorHolder;
    }

    public static ElementStateHolder of(String name, LocatorHolder locatorHolder) {
        return new ElementStateHolder(name, locatorHolder);
    }

    public String getName() {
        return name;
    }

    public LocatorHolder getLocatorHolder() {
        return locatorHolder;
    }

    public String toString() {
        return "ElementStateHolder {name = " + name + ", locator = " + locatorHolder.toString() + "}";
    }

}
