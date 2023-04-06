package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface CheckboxWebBlock extends WebBlock<CheckboxWebBlock> {

    @Name("Select")
    @Name("Чекбокс")
    @WebSelector(xpath = ".//input[@itemid = 'checkbox']/parent::node()")
    WebCheckbox checkbox();

}
