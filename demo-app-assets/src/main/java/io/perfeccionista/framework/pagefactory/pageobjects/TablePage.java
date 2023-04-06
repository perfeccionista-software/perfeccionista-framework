package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelector;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebItemSelector;
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

    public static final String CHECKBOX = "Checkbox";
    public static final String NUMBER = "Number";
    public static final String SHORT_COUNTRY_NAME = "Short country name";
    public static final String FULL_COUNTRY_NAME = "Full country name";
    public static final String POPULATION = "Population";

    @Name("Table of countries")
    @Name("Таблица стран")
    @WebSelector(tagName = "table", invokeOnCall = JsCheckIsDisplayed.class)
    @WebSelector(component = TABLE_HEADER, xpath = ".//thead/tr")
    @WebItemSelector(xpath = ".//tbody/tr")
    WebTable<TableHeader, TableRow> table();

    interface TableHeader extends WebBlock<TableHeader> {

        @Name(CHECKBOX)
        @WebSelector(xpath = ".//th[1]")
        HeaderWebBlock checkboxColumnHeader();

        @Name(NUMBER)
        @WebSelector(xpath = ".//th[2]")
        HeaderWebBlock numberColumnHeader();

        @Name(SHORT_COUNTRY_NAME)
        @WebSelector(xpath = ".//th[3]")
        HeaderWebBlock shortNameColumnHeader();

        @Name(FULL_COUNTRY_NAME)
        @WebSelector(xpath = ".//th[4]")
        HeaderWebBlock fullNameColumnHeader();

        @Name(POPULATION)
        @WebSelector(xpath = ".//th[5]")
        HeaderWebBlock populationColumnHeader();

    }

    interface TableRow extends WebBlock<TableRow> {

        @Name(CHECKBOX)
        @WebSelector(xpath = ".//td[1]")
        CheckboxWebBlock checkboxColumn();

        @Name(NUMBER)
        @WebSelector(xpath = ".//td[2]")
        CountryNumberWebBlock numberColumn();

        @Name(SHORT_COUNTRY_NAME)
        @WebSelector(xpath = ".//td[3]")
        CountryNameWebBlock shortNameColumn();

        @Name(FULL_COUNTRY_NAME)
        @WebSelector(xpath = ".//td[4]")
        FullCountryNameWebBlock fullNameColumn();

        @Name(POPULATION)
        @WebSelector(xpath = ".//td[5]")
        PopulationWebBlock populationColumn();

    }

}
