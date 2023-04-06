package io.perfeccionista.framework.cucumber.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.ru.Дано;
import io.perfeccionista.framework.cucumber.parameters.WebElementParameter;
import io.perfeccionista.framework.pagefactory.limiter.WebListBlockContextLimiter;
import io.perfeccionista.framework.pagefactory.elements.WebBlock;
import io.perfeccionista.framework.pagefactory.elements.WebList;

import static io.perfeccionista.framework.Web.*;
import static io.perfeccionista.framework.value.Values.intGreaterThanOrEqual;

// TODO: Wrap runLogic()
// TODO: Add step categories
public class WebSearchContextStepDefinitions implements WebStepDefinitions {

//    /**
//     *
//     * @param elementFinder -
//     * @param itemFilter -
//     */
//    @Given("user chooses in the list {webElement} blocks with")
//    @Дано("пользователь выбирает в списке {webElement} блоки, где")
//    public void userRestrictsBlocksInTheWebList(WebElementParameter<WebList> elementFinder,
//                                                WebBlockFilterBuilder<WebBlock> itemFilter) {
//        WebListBlockContextLimiter<WebBlock> limiter = selectWebListBlocks(elementFinder.getRaw(), itemFilter, intGreaterThanOrEqual(1));
//        getWebPageContext()
//                .addContextLimiter(limiter);
//    }

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
