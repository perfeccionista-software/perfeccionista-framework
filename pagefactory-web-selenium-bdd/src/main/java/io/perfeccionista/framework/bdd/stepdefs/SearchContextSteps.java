package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.context.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.context.WebTableRowContextLimiter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;

import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebListBlocks;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableCells;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableRows;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class SearchContextSteps implements WebStepDefinitions {

    /**
     *
     */
    @Given("user continues working with the page")
    @Given("пользователь продолжает работать со страницей")
    public void userRemovesRestrictions() {
        getWebPageContext()
                .removeContextLimiters();
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses for work in the {webElement} blocks with")
    @Given("пользователь выбирает в {webElement} для работы блоки, в которых")
    public void userRestrictsBlocksInTheWebList(WebElementParameter<WebList> elementFinder,
                                                WebListFilterBuilder itemFilter) {
        getWebPageContext()
                .addContextLimiter(selectWebListBlocks(elementFinder.getRaw(), itemFilter, intGreaterThanOrEqual(1)));
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses for work in the {webElement} rows with")
    @Given("пользователь выбирает в {webElement} для работы строки, в которых")
    public void userRestrictsRowsInTheWebTable(WebElementParameter<WebTable> elementFinder,
                                               WebTableFilterBuilder itemFilter) {
        getWebPageContext()
                .addContextLimiter(selectWebTableRows(elementFinder.getRaw(), itemFilter, intGreaterThanOrEqual(1)));
    }

    /**
     *
     * @param elementFinder -
     * @param tableColumn -
     * @param itemFilter -
     */
    @Given("user chooses for work in the {webElement} cells from column {webTableColumn} with")
    @Given("пользователь выбирает в {webElement} для работы ячейки из колонки {webTableColumn}, в которых")
    public void userRestrictsCellsInTheWebTable(WebElementParameter<WebTable> elementFinder,
                                                WebTableColumnParameter tableColumn,
                                                WebTableFilterBuilder itemFilter) {
        getWebPageContext()
                .addContextLimiter(selectWebTableCells(elementFinder.getRaw(), tableColumn.getRaw(), itemFilter, intGreaterThanOrEqual(1)));
    }

}
