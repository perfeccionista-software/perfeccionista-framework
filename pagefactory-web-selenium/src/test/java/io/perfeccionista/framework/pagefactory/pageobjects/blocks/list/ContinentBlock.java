package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextBlock;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebBlock;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.LI;

public interface ContinentBlock extends WebBlock {

    @Name("Continent name")
    @WebLocator(xpath = "self::node()//span[@itemid = 'continent-name']")
    WebTextBlock continentName();

    @Name("Total countries")
    @WebLocator(xpath = "self::node()//span[@itemid = 'continent-total']")
    WebTextBlock totalCountries();

    @Name("List of countries")
    @WebLocator(xpath = "self::node()//div[@itemid = 'countries-list']")
    @WebLocator(component = LI, xpath = ".//div[@itemid = 'countries-list-item']", single = false)
    @UseMappedWebBlock(CountryBlock.class)
    WebList countriesList();

}
