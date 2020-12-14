package io.perfeccionista.framework.cucumber.stepdefs.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebTable;
import io.perfeccionista.framework.pagefactory.elements.WebTextTable;
import io.perfeccionista.framework.pagefactory.filter.table.WebTableFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.texttable.WebTextTableFilterBuilder;

import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.row;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textRow;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class TableActionSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls table {webElement} to row with")
    @Given("пользователь прокручивает таблицу {webElement} до строки, где")
    public void userScrollsTableToElement(WebElementParameter<WebTable> elementFinder,
                                          WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filter(itemFilter)
                                .extractRow(row())
                                .getNotNullValue()
                                .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the text table {webElement} to row with")
    @Given("пользователь прокручивает текстовую таблицу {webElement} до строки, где")
    public void userScrollsTextTableToElement(WebElementParameter<WebTextTable> elementFinder,
                                              WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filter(itemFilter)
                                .extractRow(textRow())
                                .getNotNullValue()
                                .scrollTo());
    }

}
