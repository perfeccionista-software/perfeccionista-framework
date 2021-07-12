package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.LI;

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
    @WebLocator(component = LI, xpath = ".//div[@itemid = 'countries-list-item']", single = false)
    WebList<CountryBlock> countriesList();

}
