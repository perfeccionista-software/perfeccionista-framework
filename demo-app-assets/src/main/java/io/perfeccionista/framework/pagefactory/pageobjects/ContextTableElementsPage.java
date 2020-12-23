package io.perfeccionista.framework.pagefactory.pageobjects;

import io.perfeccionista.framework.name.Name;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.locators.WebLocator;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumn;
import io.perfeccionista.framework.pagefactory.elements.mapping.UseMappedWebTableColumns;
import io.perfeccionista.framework.pagefactory.jsfunction.CheckIsDisplayed;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.ContinentNameWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.CountriesTableWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.HeaderWebBlock;
import io.perfeccionista.framework.pagefactory.pageobjects.blocks.table.TotalCountriesWebBlock;

import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.TBODY_ROW;
import static io.perfeccionista.framework.pagefactory.elements.components.WebComponents.THEAD_ROW;

@Name("Page for Table context")
@Name("Страница для контекста в таблицах")
public interface ContextTableElementsPage extends AbstractWebPage {

    String CONTINENT = "Continent";
    String TOTAL = "Total";
    String COUNTRIES = "Countries";

    // TODO: Важно! В доке написать про то, что если у вас таблица в таблице, то локаторы нужно писать строгие,
    //  иначе в список дочерних элементов (ячейки родительской таблицы) будут попадать элементы из вложенных таблиц (ячейки из вложенных таблиц)
    //  и все будет ломаться
    @Name("Table of continents")
    @Name("Таблица континентов")
    @WebLocator(id = "continents-table", invokeOnCall = {CheckIsDisplayed.class})
    @WebLocator(component = THEAD_ROW, xpath = "./thead/tr")
    @WebLocator(component = TBODY_ROW, xpath = "./tbody/tr", single = false)
    @UseMappedWebTableColumns({
            @UseMappedWebTableColumn(name = CONTINENT,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[1]"),
                    bodyClass = ContinentNameWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[1]")),
            @UseMappedWebTableColumn(name = TOTAL,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[2]"),
                    bodyClass = TotalCountriesWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[2]")),
            @UseMappedWebTableColumn(name = COUNTRIES,
                    headerClass = HeaderWebBlock.class, headerLocator = @WebLocator(xpath = "./th[3]"),
                    bodyClass = CountriesTableWebBlock.class, bodyLocator = @WebLocator(xpath = "./td[3]"))
    })
    WebTable continentsTable();

}
