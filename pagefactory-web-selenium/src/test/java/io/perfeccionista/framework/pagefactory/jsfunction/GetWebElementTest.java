package io.perfeccionista.framework.pagefactory.jsfunction;

import io.perfeccionista.framework.AbstractWebSeleniumParallelTest;
import io.perfeccionista.framework.Environment;
import io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper;
import io.perfeccionista.framework.pagefactory.dispatcher.WebBrowserDispatcher;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorChain;
import io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorHolder;
import io.perfeccionista.framework.pagefactory.operation.handler.JsScrollTo;
import io.perfeccionista.framework.pagefactory.operation.handler.SeleniumGetWebElement;
import io.perfeccionista.framework.pagefactory.operation.WebElementOperation;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static io.perfeccionista.framework.invocation.wrapper.MultipleAttemptInvocationWrapper.repeatInvocation;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.ID;
import static io.perfeccionista.framework.pagefactory.elements.selectors.WebSelectorStrategy.EQUALS_TEXT;

class GetWebElementTest extends AbstractWebSeleniumParallelTest {

    @Test
    void singleElementTest(Environment environment) {
        WebBrowserDispatcher chrome = openDefaultBrowser();

        repeatInvocation(() -> {
            WebSelectorChain linkLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", EQUALS_TEXT, "Text List Elements"));
            WebElementOperation<WebElement> getLinkWebElementOperation = WebElementOperation.of(linkLocatorChain, new SeleniumGetWebElement());
            chrome.executor()
                    .executeWebElementOperation(getLinkWebElementOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult()
                    .click();
        });
        String text = MultipleAttemptInvocationWrapper.repeatInvocation(() -> {
            WebSelectorChain listElementLocatorChain = WebSelectorChain.empty()
                    .addLastSelector(WebSelectorHolder.of("ROOT", ID, "text-list"))
                    .addLastSelector(WebSelectorHolder.of("LI", EQUALS_TEXT, "Ливан")
                            .addInvokedOnCallFunction(new JsScrollTo()));
            WebElementOperation<WebElement> getListWebElementOperation = WebElementOperation.of(listElementLocatorChain, new SeleniumGetWebElement());
            return chrome.executor()
                    .executeWebElementOperation(getListWebElementOperation)
                    .ifException((exceptionMapper, exception) -> {
                        throw exceptionMapper.map(exception);
                    })
                    .getResult()
                    .getText();
        });
        System.out.println(text);
    }


}
