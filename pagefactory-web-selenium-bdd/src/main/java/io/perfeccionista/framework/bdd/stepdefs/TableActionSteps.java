package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
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
    @Given("user scrolls the {webElement} to row with")
    @Given("пользователь прокручивает {webElement} до строки, в которой")
    public void userScrollsTableToElement(WebElementParameter<WebTable> elementFinder,
                                          WebTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTable.class)
                                .filter(itemFilter)
                                .extractRow(row())
                                .should(haveNotNullResults()).getValue()
                                .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to row")
    @Given("пользователь прокручивает {webElement} до строки")
    public void userScrollsTextTableToElement(WebElementParameter<WebTextTable> elementFinder,
                                              WebTextTableFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                        elementFinder.getElement(context, WebTextTable.class)
                                .filter(itemFilter)
                                .extractRow(textRow())
                                .should(haveNotNullResults()).getValue()
                                .scrollTo());
    }

}
