package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface TotalCountriesWebBlock extends WebBlock<TotalCountriesWebBlock> {

    @Name("Total countries")
    @Name("Всего стран")
    @WebSelector(xpath = "self::node()//span[@itemid = 'continent-total']")
    WebText fullName();

}
