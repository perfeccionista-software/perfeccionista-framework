package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface DefaultWebRadioButtonBlock extends WebBlock {

    @WebLocator(xpath = ".//input[@type = 'radio']/parent::node()")
    WebRadioButton radioButton();

}
