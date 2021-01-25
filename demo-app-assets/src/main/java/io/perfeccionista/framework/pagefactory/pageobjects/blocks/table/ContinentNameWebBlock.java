package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface ContinentNameWebBlock extends WebBlock {

    @Name("Continent name")
    @Name("Название континента")
    @WebLocator(xpath = ".//span[@itemid = 'continent-name']")
    WebText continentName();

}
