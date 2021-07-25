package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CheckboxWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountryNumberWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.FullCountryNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.HeaderWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.PopulationWebBlock;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

@Name("Page with table of countries")
@Name("Страница с таблицей стран")
public interface TablePage extends AbstractWebPage {

    String CHECKBOX = "Checkbox";
    String NUMBER = "Number";
    String SHORT_COUNTRY_NAME = "Short country name";
    String FULL_COUNTRY_NAME = "Full country name";
    String POPULATION = "Population";

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebLocator(tagName = "table", invokeOnCall = JsCheckIsDisplayed.class)
    @WebLocator(component = TABLE_HEADER, xpath = ".//thead/tr")
    @WebItemLocator(xpath = ".//tbody/tr")
    WebTable<TableHeader, TableRow> table();

    interface TableHeader extends WebBlock {

        @Name(CHECKBOX)
        @WebLocator(xpath = ".//th[1]")
        HeaderWebBlock checkboxColumnHeader();

        @Name(NUMBER)
        @WebLocator(xpath = ".//th[2]")
        HeaderWebBlock numberColumnHeader();

        @Name(SHORT_COUNTRY_NAME)
        @WebLocator(xpath = ".//th[3]")
        HeaderWebBlock shortNameColumnHeader();

        @Name(FULL_COUNTRY_NAME)
        @WebLocator(xpath = ".//th[4]")
        HeaderWebBlock fullNameColumnHeader();

        @Name(POPULATION)
        @WebLocator(xpath = ".//th[5]")
        HeaderWebBlock populationColumnHeader();

    }

    interface TableRow extends WebBlock {

        @Name(CHECKBOX)
        @WebLocator(xpath = ".//td[1]")
        CheckboxWebBlock checkboxColumn();

        @Name(NUMBER)
        @WebLocator(xpath = ".//td[2]")
        CountryNumberWebBlock numberColumn();

        @Name(SHORT_COUNTRY_NAME)
        @WebLocator(xpath = ".//td[3]")
        CountryNameWebBlock shortNameColumn();

        @Name(FULL_COUNTRY_NAME)
        @WebLocator(xpath = ".//td[4]")
        FullCountryNameWebBlock fullNameColumn();

        @Name(POPULATION)
        @WebLocator(xpath = ".//td[5]")
        PopulationWebBlock populationColumn();

    }

}
