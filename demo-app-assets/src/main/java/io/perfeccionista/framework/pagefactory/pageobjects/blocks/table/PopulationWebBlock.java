package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface PopulationWebBlock extends WebBlock {

    @Name("Population")
    @Name("Население")
    @WebLocator(xpath = "self::node()//span[@itemid = 'population-number']")
    WebText population();

    @Name("Population unit")
    @Name("Единица изменения населения")
    @WebLocator(xpath = "self::node()//span[@itemid = 'population-unit']")
    @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false)
    WebText populationUnit();

}
