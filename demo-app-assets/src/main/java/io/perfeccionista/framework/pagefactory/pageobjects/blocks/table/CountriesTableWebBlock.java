package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

public interface CountriesTableWebBlock extends WebBlock {

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebLocator(xpath = ".//table[@itemid = 'countries-table']", strictSearch = false)
    @WebLocator(component = TABLE_HEADER, xpath = "./thead/tr")
    @WebItemLocator(xpath = "./tbody/tr")
    WebTable<CountriesTableHeader, CountriesTableRow> countriesTable();

    @Name("table header")
    interface CountriesTableHeader extends WebBlock {

        @WebLocator(xpath = "./th[1]")
        HeaderWebBlock checkboxColumnHeader();

        @WebLocator(xpath = "./th[2]")
        HeaderWebBlock shortNameColumnHeader();

        @WebLocator(xpath = "./th[3]")
        HeaderWebBlock fullNameColumnHeader();

        @WebLocator(xpath = "./th[4]")
        HeaderWebBlock populationColumnHeader();

    }

    @Name("table row")
    interface CountriesTableRow extends WebBlock {

        @WebLocator(xpath = "./td[1]")
        CheckboxWebBlock checkboxColumn();

        @WebLocator(xpath = "./td[2]")
        CountryNameWebBlock shortNameColumn();

        @WebLocator(xpath = "./td[3]")
        FullCountryNameWebBlock fullNameColumn();

        @WebLocator(xpath = "./td[4]")
        PopulationWebBlock populationColumn();

    }

}
