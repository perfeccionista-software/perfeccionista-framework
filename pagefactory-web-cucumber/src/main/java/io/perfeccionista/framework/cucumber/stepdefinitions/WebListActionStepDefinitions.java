package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.WebDropDownAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;

import static io.perfeccionista.framework.Web.*;

// TODO: Add step categories
public class WebListActionStepDefinitions implements WebStepDefinitions {

    /**
     *
     * @param elementFinder -
     */
    @Дано("пользователь открывает {webElement}")
    @Given("user opens {webElement}")
    public void userOpensList(WebElementParameter<WebDropDownAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebDropDownAvailable.class)
                        .open());
    }

    /**
     *
     * @param elementFinder -
     */
    @Дано("пользователь закрывает {webElement}")
    @Given("user closes {webElement}")
    public void userClosesList(WebElementParameter<WebDropDownAvailable> elementFinder) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebDropDownAvailable.class)
                        .close());
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь прокручивает список {webElement} до блока, где")
    @Given("user scrolls the list {webElement} to block with")
    public void userScrollsListToElement(WebElementParameter<WebList> elementFinder,
                                         WebListFilterBuilder<WebBlock> itemFilter) {
        getWebPageContext().execute(context -> {
            WebList<WebBlock> element = (WebList<WebBlock>) elementFinder.getElement(context, WebList.class);
            element.filter(block -> itemFilter)
                    .extractOne(block())
                    .should(haveNotNullResults())
                    .getNotNullResult()
                    .scrollTo();
        });
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь выбирает в текстовом списке {webElement} значение, где")
    @Given("user selects in text list {webElement} value with")
    public void userSelectsItemInTextList(WebElementParameter<WebTextDropDownList> elementFinder,
                                          WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context -> {
            WebTextDropDownList element = elementFinder.getElement(context, WebTextDropDownList.class);
            element.open()
                    .should(beOpen());
            element.filter(itemFilter)
                    .extractOne(textBlockElement())
                    .should(haveNotNullResults())
                    .getNotNullResult()
                    .click();
            element.close()
                    .should(beClosed());
        });
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Дано("пользователь прокручивает текстовый список {webElement} до значения, где")
    @Given("user scrolls the text list {webElement} to value with")
    public void userScrollsTextListToElement(WebElementParameter<WebTextList> elementFinder,
                                             WebTextListFilterBuilder itemFilter) {
        getWebPageContext().execute(context ->
                elementFinder.getElement(context, WebTextList.class)
                        .filter(itemFilter)
                        .extractOne(textBlock())
                        .should(haveNotNullResults())
                        .getNotNullResult()
                        .scrollTo());
    }

}
