package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
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
public class TableActionSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to row with")
    @Given("пользователь прокручивает {webElement} до строки, в которой")
    public void userScrollsTableToElement(WebElementParameter<WebTable> elementFinder,
                                          WebTableFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element.filter(itemFilter)
                        .extractOneRow(row())
                        .should(haveNotNullResults())
                        .getValue()
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
        elementFinder.find()
                .forEachOrdered(element -> element.filter(itemFilter)
                        .extractOneRow(textRow())
                        .should(haveNotNullResults())
                        .getValue()
                        .scrollTo());
    }

}
