package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementProperty;

public interface CountryBlock extends WebBlock {

    @Name("Select")
    @Name("Чекбокс")
    @WebLocator(xpath = "self::node()//input[@itemid = 'checkbox']/parent::node()")
    WebCheckbox checkbox();

    @Name("Number")
    @Name("Номер")
    @WebLocator(xpath = "self::node()//span[@itemid = 'number']")
    WebText number();

    @Name("Country name")
    @Name("Название государства")
    @WebLocator(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebElementProperty(name = "Wiki link", params = "href")
    WebLink shortName();

    @Name("Full country name")
    @Name("Полное название государства")
    @WebLocator(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebLocator(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    @WebElementProperty(name = "prompt", params = "title")
    WebText fullName();

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
