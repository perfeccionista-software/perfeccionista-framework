package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.THEAD_ROW;

public interface CountriesTableWebBlock extends WebBlock {

    String CHECKBOX = "Checkbox";
    String SHORT_NAME = "Short country name";
    String FULL_NAME = "Full country name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebLocator(xpath = ".//table[@itemid = 'countries-table']", strictSearch = false)
    @WebLocator(component = THEAD_ROW, xpath = "./thead/tr")
    @WebLocator(component = TBODY_ROW, xpath = "./tbody/tr", single = false)
    @UseMappedWebTableColumn(name = CHECKBOX,
            headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[1]"),
            bodyClass = CheckboxWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[1]"))
    @UseMappedWebTableColumn(name = SHORT_NAME,
            headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[2]"),
            bodyClass = CountryNameWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[2]"))
    @UseMappedWebTableColumn(name = FULL_NAME,
            headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[3]"),
            bodyClass = FullCountryNameWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[3]"))
    @UseMappedWebTableColumn(name = POPULATION,
            headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[4]"),
            bodyClass = PopulationWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[4]"))
    WebTable countriesTable();

}
