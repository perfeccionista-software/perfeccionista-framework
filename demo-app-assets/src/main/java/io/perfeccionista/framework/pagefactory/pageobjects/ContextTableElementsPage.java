package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebItemLocator;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.operation.handler.JsCheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountriesTableWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.HeaderWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.TotalCountriesWebBlock;

import static io.perfeccionista.framework.pagefactory.elements.ElementComponents.TABLE_HEADER;

@Name("Page for Table context")
@Name("Страница для контекста в таблицах")
public interface ContextTableElementsPage extends AbstractWebPage {

    // TODO: Важно! В доке написать про то, что если у вас таблица в таблице, то локаторы нужно писать строгие,
    //  иначе в список дочерних элементов (ячейки родительской таблицы) будут попадать элементы из вложенных таблиц (ячейки из вложенных таблиц)
    //  и все будет ломаться
    @Name("Table of continents")
    @Name("Таблица континентов")
    @WebLocator(id = "continents-table", invokeOnCall = JsCheckIsDisplayed.class)
    @WebLocator(component = TABLE_HEADER, xpath = "./thead/tr")
    @WebItemLocator(xpath = "./tbody/tr")
    WebTable<ContinentTableHeader, ContinentTableRow> continentsTable();

    interface ContinentTableHeader extends WebBlock {

        @WebLocator(xpath = "./th[1]")
        HeaderWebBlock continentColumnHeader();

        @WebLocator(xpath = "./th[2]")
        HeaderWebBlock totalColumnHeader();

        @WebLocator(xpath = "./th[3]")
        HeaderWebBlock countriesColumnHeader();

    }

    interface ContinentTableRow extends WebBlock {

        @WebLocator(xpath = "./td[1]")
        ContinentNameWebBlock continentColumn();

        @WebLocator(xpath = "./td[2]")
        TotalCountriesWebBlock totalColumn();

        @Name("column Countries")
        @WebLocator(xpath = "./td[3]")
        CountriesTableWebBlock countriesColumn();

    }

}
