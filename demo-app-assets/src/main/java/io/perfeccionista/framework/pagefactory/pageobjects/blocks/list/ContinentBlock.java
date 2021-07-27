package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

public interface ContinentBlock extends WebBlock {

    @Name("Continent name")
    @Name("Название континента")
    @WebLocator(xpath = "self::node()//span[@itemid = 'continent-name']")
    WebText continentName();

    @Name("Total countries")
    @Name("Всего стран")
    @WebLocator(xpath = "self::node()//span[@itemid = 'continent-total']")
    WebText totalCountries();

    @Name("List of countries")
    @Name("Список стран")
    @WebLocator(xpath = "self::node()//div[@itemid = 'countries-list']")
    @WebItemLocator(xpath = ".//div[@itemid = 'countries-list-item']")
    WebList<CountryBlock> countriesList();

}
