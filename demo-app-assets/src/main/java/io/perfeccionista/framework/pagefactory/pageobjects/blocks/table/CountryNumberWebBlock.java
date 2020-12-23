package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface CountryNumberWebBlock extends WebBlock {

    @Name("Number")
    @Name("Номер")
    @WebLocator(xpath = "self::node()")
    WebTextBlock number();

}
