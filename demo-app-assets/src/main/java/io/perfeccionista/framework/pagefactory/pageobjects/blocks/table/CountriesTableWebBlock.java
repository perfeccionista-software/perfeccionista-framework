package io.perfeccionista.framework.pagefactory.pageobjects.blocks.table;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

public interface CountriesTableWebBlock extends WebBlock<CountriesTableWebBlock> {

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebSelector(xpath = ".//table[@itemid = 'countries-table']", strictSearch = false)
    @WebSelector(component = TABLE_HEADER, xpath = "./thead/tr")
    @WebItemSelector(xpath = "./tbody/tr")
    WebTable<CountriesTableHeader, CountriesTableRow> countriesTable();

    @Name("table header")
    interface CountriesTableHeader extends WebBlock<CountriesTableHeader> {

        @WebSelector(xpath = "./th[1]")
        HeaderWebBlock checkboxColumnHeader();

        @WebSelector(xpath = "./th[2]")
        HeaderWebBlock shortNameColumnHeader();

        @WebSelector(xpath = "./th[3]")
        HeaderWebBlock fullNameColumnHeader();

        @WebSelector(xpath = "./th[4]")
        HeaderWebBlock populationColumnHeader();

    }

    @Name("table row")
    interface CountriesTableRow extends WebBlock<CountriesTableRow> {

        @WebSelector(xpath = "./td[1]")
        CheckboxWebBlock checkboxColumn();

        @WebSelector(xpath = "./td[2]")
        CountryNameWebBlock shortNameColumn();

        @WebSelector(xpath = "./td[3]")
        FullCountryNameWebBlock fullNameColumn();

        @WebSelector(xpath = "./td[4]")
        PopulationWebBlock populationColumn();

    }

}
