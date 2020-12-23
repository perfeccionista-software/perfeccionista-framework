package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface CheckboxWebBlock extends WebBlock {

    @Name("Select")
    @Name("Чекбокс")
    @WebLocator(xpath = ".//input[@itemid = 'checkbox']/parent::node()")
    WebCheckbox checkbox();

}
