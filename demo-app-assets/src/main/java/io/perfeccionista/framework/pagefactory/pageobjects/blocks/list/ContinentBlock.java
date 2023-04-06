package io.perfeccionista.framework.pagefactory.pageobjects.blocks.list;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebText;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

public interface ContinentBlock extends WebBlock<ContinentBlock> {

    @Name("Continent name")
    @Name("Название континента")
    @WebSelector(xpath = "self::node()//span[@itemid = 'continent-name']")
    WebText continentName();

    @Name("Total countries")
    @Name("Всего стран")
    @WebSelector(xpath = "self::node()//span[@itemid = 'continent-total']")
    WebText totalCountries();

    @Name("List of countries")
    @Name("Список стран")
    @WebSelector(xpath = "self::node()//div[@itemid = 'countries-list']")
    @WebItemSelector(xpath = ".//div[@itemid = 'countries-list-item']")
    WebList<CountryBlock> countriesList();

}
