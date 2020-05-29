package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.base.ChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public interface WebElementPropertyExtractor<T extends ChildElement<?>> {

    String extract(T element, WebLocatorHolder locatorHolder);

}
