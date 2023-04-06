package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface WebTextBlock extends WebBlock<WebTextBlock> {

    @WebSelector(selfNode = true)
    WebText text();

}
