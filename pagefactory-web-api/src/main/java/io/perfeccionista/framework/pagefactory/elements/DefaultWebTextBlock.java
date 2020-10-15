package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface DefaultWebTextBlock extends WebBlock {

    @WebLocator(xpath = "self::node()")
    WebLink textLink();

}
