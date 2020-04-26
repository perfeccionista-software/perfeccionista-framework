package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.bdd.parameters.WebTableColumnParameter;
import io.perfeccionista.framework.pagefactory.browser.WebBrowserService;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.context.WebBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableCellContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.context.WebTableRowContextLimiter;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilter;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilter;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class SearchContextSteps implements EnvironmentAvailable {

    /**
     *
     */
    @Given("user continues working with the page")
    @Given("пользователь продолжает работать со страницей")
    public void userRemovesRestrictions() {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .removeLimiters();
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user chooses for work {webElement}")
    @Given("пользователь выбирает для работы {webElement}")
    public void userRestrictsBlocksInTheWebList(WebElementParameter<WebBlock> elementFinder) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .setLimiter(new WebBlockContextLimiter<>(elementFinder.findSingle()));
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses for work in the {webElement} blocks with")
    @Given("пользователь выбирает в {webElement} для работы блоки, в которых")
    public void userRestrictsBlocksInTheWebList(WebElementParameter<WebList> elementFinder,
                                                WebListFilter itemFilter) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .setLimiter(new WebListBlockContextLimiter<>(elementFinder.findSingle(), itemFilter));
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user chooses for work in the {webElement} rows with")
    @Given("пользователь выбирает в {webElement} для работы строки, в которых")
    public void userRestrictsRowsInTheWebTable(WebElementParameter<WebTable> elementFinder,
                                               WebTableFilter itemFilter) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .setLimiter(new WebTableRowContextLimiter(elementFinder.findSingle(), itemFilter));
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
                                                WebTableFilter itemFilter) {
        getEnvironment().getService(WebBrowserService.class)
                .getActiveDispatcher()
                .getPageContext()
                .setLimiter(new WebTableCellContextLimiter<>(elementFinder.findSingle(), tableColumn.getRaw(), itemFilter));
    }

}
