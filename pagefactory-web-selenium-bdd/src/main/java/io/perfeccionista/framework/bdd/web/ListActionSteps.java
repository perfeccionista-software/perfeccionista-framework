package io.perfeccionista.framework.bdd.web;

import io.cucumber.java.en.Given;
import io.perfeccionista.framework.bdd.EnvironmentAvailable;
import io.perfeccionista.framework.bdd.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.elements.WebDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebList;
import io.perfeccionista.framework.pagefactory.elements.WebTextDropDownList;
import io.perfeccionista.framework.pagefactory.elements.WebTextList;
import io.perfeccionista.framework.pagefactory.elements.methods.CloseAvailable;
import io.perfeccionista.framework.pagefactory.elements.methods.OpenAvailable;
import io.perfeccionista.framework.pagefactory.filter.list.WebListFilterBuilder;
import io.perfeccionista.framework.pagefactory.filter.textlist.WebTextListFilterBuilder;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class ListActionSteps implements EnvironmentAvailable {

    /**
     *
     * @param elementFinder -
     */
    @Given("user opens {webElement}")
    @Given("пользователь открывает {webElement}")
    public void userOpensList(WebElementParameter<OpenAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(OpenAvailable::open);
    }

    /**
     *
     * @param elementFinder -
     */
    @Given("user closes {webElement}")
    @Given("пользователь закрывает {webElement}")
    public void userClosesList(WebElementParameter<CloseAvailable> elementFinder) {
        elementFinder.find()
                .forEachOrdered(CloseAvailable::close);
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
        elementFinder.find()
                .forEachOrdered(element -> element.scrollToElement(itemFilter));
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user scrolls the {webElement} to value")
    @Given("пользователь прокручивает {webElement} до значения")
    public void userScrollsTextListToElement(WebElementParameter<WebTextList> elementFinder,
                                             WebTextListFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element.scrollToElement(itemFilter));
    }

    /**
     *
     * @param elementFinder -
     * @param itemFilter -
     */
    @Given("user selects in the {webElement} block with")
    @Given("пользователь выбирает в {webElement} блок, в котором")
    public void userSelectsItemInList(WebElementParameter<WebDropDownList> elementFinder,
                                      WebListFilterBuilder itemFilter) {
        elementFinder.find()
                .forEachOrdered(element -> element
                        .open()
// FIXME Method not found
//                        .clickToElement(itemFilter)
                        .close());
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
        elementFinder.find()
                .forEachOrdered(element -> element
                        .open()
                        .clickToElement(itemFilter)
                        .close());
    }

}
