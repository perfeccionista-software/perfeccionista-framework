package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;

import static io.perfeccionista.framework.Web.*;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebTableActionStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь прокручивает таблицу {webElement} до строки, где")
    @Given("user scrolls table {webElement} to row with")
    public void userScrollsTableToElement(WebElementParameter<WebTable> elementFinder,
                                          WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filterBuilder(itemFilter)
                                .extractRow(row())
                                .getNotNullResult()
                                .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь прокручивает текстовую таблицу {webElement} до строки, где")
    @Given("user scrolls the text table {webElement} to row with")
    public void userScrollsTextTableToElement(WebElementParameter<WebTextTable> elementFinder,
                                              WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filterBuilder(itemFilter)
                                .extractRow(textRow())
                                .getNotNullResult()
                                .scrollTo());
    }

}
