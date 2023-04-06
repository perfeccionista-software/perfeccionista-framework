package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface CountryNumberWebBlock extends WebBlock<CountryNumberWebBlock> {

    @Name("Number")
    @Name("Номер")
    @WebSelector(selfNode = true)
    WebText number();

}
