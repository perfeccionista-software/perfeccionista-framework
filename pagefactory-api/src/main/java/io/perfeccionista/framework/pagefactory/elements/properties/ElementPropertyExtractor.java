package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.LocatorHolder;

public interface ElementPropertyExtractor<T extends ChildElement<?>> {

    String extract(T element, LocatorHolder locatorHolder);

}
