package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface CountryBlock extends WebBlock<CountryBlock> {

    @Name("Select")
    @Name("Чекбокс")
    @WebSelector(xpath = "self::node()//input[@itemid = 'checkbox']/parent::node()")
    WebCheckbox checkbox();

    @Name("Number")
    @Name("Номер")
    @WebSelector(xpath = "self::node()//span[@itemid = 'number']")
    WebText number();

    @Name("Country name")
    @Name("Название государства")
    @WebSelector(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebSelector(component = "Self", xpath = "self::node()", strictSearch = false) // В некоторых ячейках этого элемента нет
    WebLink shortName();

    @Name("Full country name")
    @Name("Полное название государства")
    @WebSelector(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebSelector(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    WebText fullName();

    @Name("Population")
    @Name("Население")
    @WebSelector(xpath = "self::node()//span[@itemid = 'population-number']")
    WebText population();

    @Name("Population unit")
    @Name("Единица изменения населения")
    @WebSelector(xpath = "self::node()//span[@itemid = 'population-unit']")
    @WebSelector(component = "Self", xpath = "self::node()", strictSearch = false)
    WebText populationUnit();

}
