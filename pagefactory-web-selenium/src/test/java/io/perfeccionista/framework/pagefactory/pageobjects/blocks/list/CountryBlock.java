package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebCheckbox;
import io.perfeccionista.framework.pagefactory.elements.WebLink;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.properties.base.WebElementProperty;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.HrefAttributeExtractor;
import io.perfeccionista.framework.pagefactory.pageobjects.extractors.TitleAttributeExtractor;

public interface CountryBlock extends WebBlock {

    @Name("Select")
    @WebLocator(xpath = "self::node()//input[@itemid = 'checkbox']/parent::node()")
    WebCheckbox checkbox();

    @Name("Number")
    @WebLocator(xpath = "self::node()//span[@itemid = 'number']")
    WebTextBlock number();

    @Name("Short name")
    @WebLocator(xpath = "self::node()//a[@itemid = 'country-name']", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false) // В некоторых ячейках этого элемента нет
    @WebElementProperty(name = "Wiki link", extractor = HrefAttributeExtractor.class)
    WebLink shortName();

    @Name("Full name")
    @WebLocator(xpath = "self::node()//span[@itemid = 'country-full-name']", strictSearch = false)
    @WebLocator(component = "SNG", xpath = "self::node()//span[@itemid = 'sng']")
    @WebElementProperty(name = "prompt", extractor = TitleAttributeExtractor.class)
    WebTextBlock fullName();

    @Name("Population")
    @WebLocator(xpath = "self::node()//span[@itemid = 'population-number']")
    WebTextBlock population();

    @Name("Population unit")
    @WebLocator(xpath = "self::node()//span[@itemid = 'population-unit']")
    @WebLocator(component = "Self", xpath = "self::node()", strictSearch = false)
    WebTextBlock populationUnit();

}