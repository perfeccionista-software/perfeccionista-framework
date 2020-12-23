package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.cucumber.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.pagefactory.context.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.context.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.context.WebTableRowContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;

import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebListBlocks;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableCells;
import static io.perfeccionista.framework.pagefactory.context.WebContextLimiters.selectWebTableRows;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebSearchContextStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses in the list {webElement} blocks with")
    @Дано("пользователь выбирает в списке {webElement} блоки, где")
    public void userRestrictsBlocksInTheWebList(WebElementParameter<WebList> elementFinder,
                                                WebListFilterBuilder itemFilter) {
        WebListBlockContextLimiter<WebBlock> limiter = selectWebListBlocks(elementFinder.getRaw(), itemFilter, intGreaterThanOrEqual(1));
        getWebPageContext()
                .addContextLimiter(limiter);
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses in the table {webElement} rows with")
    @Дано("пользователь выбирает в таблице {webElement} строки, где")
    public void userRestrictsRowsInTheWebTable(WebElementParameter<WebTable> elementFinder,
                                               WebTableFilterBuilder itemFilter) {
        WebTableRowContextLimiter limiter = selectWebTableRows(elementFinder.getRaw(), itemFilter, intGreaterThanOrEqual(1));
        getWebPageContext()
                .addContextLimiter(limiter);
    }

    /**
     *
     * @param elementFinder -
     * @param tableColumn -
     * @param itemFilter -
     */
    @Given("user chooses in the table {webElement} cells from column {webTableColumn} with")
    @Дано("пользователь выбирает в таблице {webElement} ячейки из колонки {webTableColumn}, где")
    public void userRestrictsCellsInTheWebTable(WebElementParameter<WebTable> elementFinder,
                                                WebTableColumnParameter tableColumn,
                                                WebTableFilterBuilder itemFilter) {
        WebTableCellContextLimiter<WebBlock> limiter = selectWebTableCells(elementFinder.getRaw(), tableColumn.getRaw(), itemFilter, intGreaterThanOrEqual(1));
        getWebPageContext()
                .addContextLimiter(limiter);
    }

    /**
     *
     */
    @Given("user continues working with the page")
    @Дано("пользователь продолжает работать со страницей")
    public void userRemovesRestrictions() {
        getWebPageContext()
                .removeContextLimiters();
    }

}
