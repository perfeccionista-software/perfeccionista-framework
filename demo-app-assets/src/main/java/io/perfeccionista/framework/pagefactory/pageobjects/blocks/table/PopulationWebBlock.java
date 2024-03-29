package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface PopulationWebBlock extends WebBlock<PopulationWebBlock> {

    @Name("Population")
    @Name("Население")
    @WebSelector(xpath = "self::node()//span[@itemid = 'population-number']")
    WebText population();

    @Name("Population unit")
    @Name("Единица изменения населения")
    @WebSelector(xpath = "self::node()//span[@itemid = 'population-unit']")
    @WebSelector(component = "Self", selfNode = true, strictSearch = false)
    WebText populationUnit();

}
