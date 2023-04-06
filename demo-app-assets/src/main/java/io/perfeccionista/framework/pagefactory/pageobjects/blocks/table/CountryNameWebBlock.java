package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface CountryNameWebBlock extends WebBlock<CountryNameWebBlock> {

    @Name("Country name")
    @Name("Название государства")
    @WebSelector(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebSelector(component = "Self", selfNode = true, strictSearch = false) // В некоторых ячейках этого элемента нет
    WebLink shortName();

}
