package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementAttributeProperty;

public interface CountryNameWebBlock extends WebBlock {

    @Name("Country name")
    @Name("Название государства")
    @WebLocator(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebLocator(component = "Self", selfNode = true, strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebElementAttributeProperty(name = "Wiki link", attribute = "href")
    WebLink shortName();

}
