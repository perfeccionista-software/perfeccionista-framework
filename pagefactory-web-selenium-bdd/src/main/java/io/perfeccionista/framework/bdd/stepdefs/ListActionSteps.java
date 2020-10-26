package io.perfeccionista.framework.bdd.stepdefs;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;

import static io.perfeccionista.framework.matcher.WebElementAssertions.beClosed;
import static io.perfeccionista.framework.matcher.WebElementAssertions.beOpen;
import static io.perfeccionista.framework.matcher.WebMultipleResultAssertions.haveNotNullResults;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.block;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textBlock;
import static io.perfeccionista.framework.pagefactory.extractor.WebExtractors.textBlockElement;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class ListActionSteps implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Given("user opens {webElement}")
    @Given("пользователь открывает {webElement}")
    public void userOpensList(WebElementParameter<OpenAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, OpenAvailable.class)
                        .open());
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user closes {webElement}")
    @Given("пользователь закрывает {webElement}")
    public void userClosesList(WebElementParameter<CloseAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, CloseAvailable.class)
                        .close());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to block with")
    @Given("пользователь прокручивает {webElement} до блока, в котором")
    public void userScrollsListToElement(WebElementParameter<WebList> elementFinder,
                                         WebListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebList.class)
                        .filter(itemFilter)
                        .extractOne(block())
                        .should(haveNotNullResults())
                        .getValue()
                        .scrollTo());
    }

    // WebTextList

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to value")
    @Given("пользователь прокручивает {webElement} до значения")
    public void userScrollsTextListToElement(WebElementParameter<WebTextList> elementFinder,
                                             WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTextList.class)
                        .filter(itemFilter)
                        .extractOne(textBlock())
                        .should(haveNotNullResults())
                        .getValue()
                        .scrollTo());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user selects in the {webElement} value")
    @Given("пользователь выбирает в {webElement} значение")
    public void userSelectsItemInTextList(WebElementParameter<WebTextDropDownList> elementFinder,
                                          WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context -> {
                    WebTextDropDownList element = elementFinder.getElement(context, WebTextDropDownList.class);
                    element.open()
                            .should(beOpen());
                    element.filter(itemFilter)
                            .extractOne(textBlockElement())
                            .should(haveNotNullResults())
                            .getValue()
                            .click();
                    element.close()
                            .should(beClosed());
                });
    }

}
