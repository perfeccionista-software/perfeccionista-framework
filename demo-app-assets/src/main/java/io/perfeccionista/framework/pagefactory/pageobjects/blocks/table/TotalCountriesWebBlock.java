package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface TotalCountriesWebBlock extends WebBlock {

    @Name("Total countries")
    @Name("Всего стран")
    @WebLocator(xpath = "self::node()//span[@itemid = 'continent-total']")
    WebTextBlock fullName();

}
