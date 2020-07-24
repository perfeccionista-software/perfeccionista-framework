package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface TextWebMappedBlock extends WebMappedBlock {

    @WebLocator(xpath = "self::node()")
    WebLink text();

}
