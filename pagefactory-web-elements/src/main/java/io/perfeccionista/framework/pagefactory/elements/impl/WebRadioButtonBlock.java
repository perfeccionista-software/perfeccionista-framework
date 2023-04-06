package io.perfeccionista.framework.pagefactory.elements.impl;

import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebRadioButton;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface WebRadioButtonBlock extends WebBlock<WebRadioButtonBlock> {

    @WebSelector(selfNode = true)
    WebRadioButton radio();

}
