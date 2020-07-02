package io.perfeccionista.framework.pagefactory.elements.properties;

import io.perfeccionista.framework.pagefactory.elements.base.WebChildElement;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocatorHolder;

public interface WebElementPropertyExtractor<T extends WebChildElement> {

    String extract(T element, WebLocatorHolder locatorHolder);

}
