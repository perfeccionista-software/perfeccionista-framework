package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;
import io.perfeccionista.framework.pagefactory.js.JsFunction;

/**
 * TODO: JavaDoc
 */
public class ElementPropertyHolder {

    private final String name;
    private final LocatorHolder locatorHolder;
    private final Class<? extends JsFunction<String, String>> jsFunctionClass;

    private ElementPropertyHolder(String name, LocatorHolder locatorHolder, Class<? extends JsFunction<String, String>> jsFunctionClass) {
        this.name = name;
        this.locatorHolder = locatorHolder;
        this.jsFunctionClass = jsFunctionClass;
    }

    public static ElementPropertyHolder of(String name, LocatorHolder locatorHolder, Class<? extends JsFunction<String, String>> jsFunction) {
        return new ElementPropertyHolder(name, locatorHolder, jsFunction);
    }

    public String getName() {
        return name;
    }

    public LocatorHolder getLocatorHolder() {
        return locatorHolder;
    }

    public Class<? extends JsFunction<String, String>> getJsFunctionClass() {
        return jsFunctionClass;
    }

    public String toString() {
        return "ElementPropertyHolder {name = " + name + ", locator = " + locatorHolder.toString() + ", jsFunctionClass = " + jsFunctionClass.getCanonicalName() + "}";
    }

}
